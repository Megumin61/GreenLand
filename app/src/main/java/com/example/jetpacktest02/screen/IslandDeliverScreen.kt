package com.example.jetpacktest02.screen


import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.ExploreMemberItem
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun IslandDeliverScreen(
    nav01: () -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController
) {
    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Flesh1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )

    Surface(modifier = Modifier.fillMaxSize()) {
        //申请照相机权限
        val permissionState =
            rememberPermissionState(permission = "android.permission.CAMERA")

        //页面模式（留言模式:0 / 照片模式:1）
//        var deliverPageMode by remember {
//            mutableStateOf(0)
//        }
        var deliverBtnOffset by remember {
            mutableStateOf(0.dp)
        }

        if (userViewModel.uiState.value.deliverPageMode.value == 1) {
            deliverBtnOffset = 30.dp
        } else {
            deliverBtnOffset = 0.dp
        }

        //输入框文字
        var text by remember { mutableStateOf("") }

        //SnackBar状态变量
        val snackbarHostState = remember { SnackbarHostState() }

        //底部抽屉 状态变量
        val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()

        //顶部菜单栏
        Scaffold(
            snackbarHost = {
                androidx.compose.material3.SnackbarHost(snackbarHostState) { data ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        androidx.compose.material3.Snackbar(
                            modifier = Modifier.width(200.dp),
                            snackbarData = data,
                            containerColor = Green5,
                            contentColor = Color.White,
                            shape = RoundedCornerShape(30.dp),
                        )
                    }

                }
            },
            topBar = {
                TopAppBar(title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //设置字体粗细
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-35.dp, 0.dp)//向左偏移一段距离
                        )
                    }
                },
                    backgroundColor = Flesh1,
                    contentColor = Color.Black,
                    elevation = 0.dp, //设置阴影
                    //左侧按钮
                    navigationIcon = {

                        IconButton(onClick = nav01) {
                            Icon(
                                bitmap = ImageBitmap.imageResource(id = R.drawable.g1_2_0_ic_arrow_left),
                                contentDescription = "",
                            )
                        }
                    },
                    //右侧按钮
                    actions = { }

                )
            }
        ) {

            //页面背景颜色
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Flesh1,
                                Flesh2
                            )
                        )
                    )
            ) {
                //页面内容的最外层Col
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 40.dp, vertical = 40.dp)
                ) {
                    //切换按钮
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.g4_6_ic_toggletophoto),
                            contentDescription = null,
                            modifier = Modifier
                                .width(130.dp)
                                .offset(y = 18.dp)
                                //切换页面模式
                                .clickable {
                                    permissionState.launchPermissionRequest()
                                    if (userViewModel.uiState.value.deliverPageMode.value== 0) {
                                        userViewModel.uiState.value.deliverPageMode.value = 1 //切换成照片模式
                                    } else {
                                        userViewModel.uiState.value.deliverPageMode.value = 0
                                    }
                                },
                        )
                    }

                    //文字输入卡片
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp), horizontalArrangement = Arrangement.End
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp)
                                .padding(13.dp) // 外边距
                            ,
                            backgroundColor = Color.White,
                            shape = MaterialTheme.shapes.large,
                            elevation = 1.5.dp

                        ) {
                            when (userViewModel.uiState.value.deliverPageMode.value) {
                                0 -> {
                                    //上方区域
                                    Column(
                                        modifier = Modifier.padding(
                                            start = 14.dp,
                                            end = 14.dp,
                                            top = 20.dp,
                                            bottom = 35.dp
                                        )
                                    ) {
                                        //文字输入区
                                        Row(modifier = Modifier) {


                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .background(Color.White),
                                                contentAlignment = Alignment.TopStart
                                            ) {
                                                BasicTextField(
                                                    value = text,
                                                    onValueChange = {
                                                        text = it
                                                    },
                                                    modifier = Modifier
                                                        .background(Color.White, CircleShape)

                                                        .fillMaxWidth(),
                                                    decorationBox = { innerTextField ->
                                                        Row(
                                                            verticalAlignment = Alignment.CenterVertically,
                                                            modifier = Modifier.padding(horizontal = 10.dp)
                                                        ) {
//                                            IconButton(
//                                                onClick = { }
//                                            ) {
//                                                Icon(painterResource(id = R.drawable.g4_2_ic_friendlist), null)
//                                            }
                                                            Box(
                                                                modifier = Modifier
                                                                    .weight(1f)
                                                                    .fillMaxSize(),
                                                                contentAlignment = Alignment.TopStart
                                                            ) {
                                                                innerTextField()
                                                            }
//                                            IconButton(
//                                                onClick = { },
//                                            ) {
//                                                Icon(Icons.Filled.Send, null)
//                                            }
                                                        }
                                                    }
                                                )
                                            }
                                        }
                                    }

                                    //下方区域
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(14.dp),
                                        verticalArrangement = Arrangement.Bottom
                                    ) {
                                        //字数标注标签
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.End
                                        ) {
                                            Text(
                                                buildAnnotatedString {
                                                    withStyle(
                                                        style = SpanStyle(
                                                            fontWeight = FontWeight.W600,
                                                            fontSize = 12.sp,
                                                            color = Color(0xFF4552B8)
                                                        )
                                                    ) {
                                                        append(text.length.toString())
                                                    }
                                                    append("/100")
                                                },
                                                fontSize = 12.sp,
                                                color = Gray1
                                            )
                                        }
                                    }
                                }
                                1 -> {
                                    CameraX()
                                }
                            }


                        }
                    }

                    //发布按钮
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .offset(deliverBtnOffset),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //留言模式下
                        if (userViewModel.uiState.value.deliverPageMode.value == 0) {
                            Image(
                                painter = painterResource(id = R.drawable.g4_6_btn_sendmessage),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clickable(
                                        onClick = {
                                            scope.launch {
                                                snackbarHostState.showSnackbar(
                                                    "发送成功~"
                                                )
                                            }
                                            text = ""
                                            //清空visitItem的值
                                            userViewModel.uiState.value.visitItem.value = ExploreMemberItem()
                                        },
                                        indication = null,
                                        interactionSource = MutableInteractionSource()
                                    ),
                            )
                        }
                        //照片模式下
                        else {
                            Image(
                                painter = painterResource(id = R.drawable.g4_6_btn_camshot),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clickable(
                                        onClick = {},
                                        indication = null,
                                        interactionSource = MutableInteractionSource()
                                    ),
                            )
                            Image(
                                painter = painterResource(id = R.drawable.g4_6_btn_choosefromgallery),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(60.dp)
                                    .clickable(
                                        onClick = {},
                                        indication = null,
                                        interactionSource = MutableInteractionSource()
                                    ),
                            )
                        }


                    }

                    //显示已选中好友头像
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 45.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Surface(
                            shape = CircleShape,
                            border = BorderStroke(2.dp, Green5)
                        ) {
                            if (userViewModel.uiState.value.visitItem.value.userName != "") {
                                Image(
                                    painter = painterResource(id = userViewModel.uiState.value.visitItem.value.userAvatar),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(40.dp),
                                )
                            }

                        }
                    }

                    //选择可见好友 按钮
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.g4_6_btn_setdeliverrange1),
                            contentDescription = null,
                            modifier = Modifier
                                .width(140.dp)
                                .clickable(onClick = { scope.launch { state.show() } }),
                        )

                    }


                }

            }

            //选择可见好友 抽屉组件（需要放在背景颜色同级）
            ModalBottomSheetLayout(
                sheetState = state,
                sheetShape = MaterialTheme.shapes.large,
                sheetContent = {
                    Column {
                        ListItem(
                            text = {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "选择可见成员",
                                        style = TextStyle(
                                            fontWeight = FontWeight.W900, //设置字体粗细
                                            fontSize = 16.sp,
                                        ),
                                        modifier = Modifier
                                    )

                                    Image(
                                        painter = painterResource(id = R.drawable.g4_6_1_ic_cancel),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(20.dp)
                                            .offset(x = 160.dp)
                                            .clickable(onClick = { scope.launch { state.hide() } }),
                                    )
                                }

                            }
                        )
                        Column(
                            modifier = Modifier
                                .horizontalScroll(rememberScrollState())
                                .fillMaxWidth()
                                .padding(15.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            //第一行头像
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp),
                                horizontalArrangement = Arrangement.Start
                            ) {

                                AvatarItem(
                                    "全部好友",
                                    R.drawable.g4_6_1_ic_allcansee,
                                    userViewModel = userViewModel
                                )

                                userViewModel.uiState.value.friendListData.forEachIndexed { index, item ->
                                    if (index <= userViewModel.uiState.value.friendListData.size / 2) {
                                        AvatarItem(
                                            item.userName,
                                            item.userAvatar,
                                            userViewModel = userViewModel
                                        )
                                    }
                                }
                            }

                            //第二行头像

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                userViewModel.uiState.value.friendListData.forEachIndexed { index, item ->

                                    if (index > userViewModel.uiState.value.friendListData.size / 2) {
                                        AvatarItem(
                                            item.userName,
                                            item.userAvatar,
                                            userViewModel = userViewModel
                                        )
                                    }
                                }
                            }
                        }


                    }
                }
            ) {
            }
            //处理”返回键“事件，当抽屉展开时，触发关闭抽屉
            BackHandler(
                enabled = (state.currentValue == ModalBottomSheetValue.HalfExpanded
                        || state.currentValue == ModalBottomSheetValue.Expanded),
                onBack = {
                    scope.launch {
                        state.hide()

                    }
                }
            )

            //照相机预览组件
            PermissionRequired(
                permissionState = permissionState,
                permissionNotGrantedContent = {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(modifier = Modifier.weight(1f)) {

                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
//                            IconButton(onClick = {
//                                //要求用户授予权限
//                                permissionState.launchPermissionRequest()
//                            }) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.g0_1_ic_phone),
//                                    contentDescription = ""
//                                )
//                            }
                        }
                    }
                },
                permissionNotAvailableContent = { /*TODO*/ }) {
                when (userViewModel.uiState.value.deliverPageMode.value) {
                    0 -> {}
                    1 -> {
//                        CameraX()
                    }
                }
            }
        }

    }
}

@Composable
fun CameraX() {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val cameraProvideFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    val previewView = remember {
        PreviewView(context).apply {
            id = R.id.preview_view
        }
    }
    AndroidView(
        factory = { previewView }, modifier = Modifier
            .width(200.dp)
            .height(200.dp)
    ) {
        cameraProvideFuture.addListener({
            val cameraProvider = cameraProvideFuture.get()
            val preview = androidx.camera.core.Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview)
            } catch (e: Exception) {
                Log.e("Exception", e.toString())
            }
        }, ContextCompat.getMainExecutor(context))
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AvatarItem(
    text: String, avatar: Int,
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),

    ) {
    //头像+用户名项目
    Column(
        modifier = Modifier.width(76.dp),
        verticalArrangement = Arrangement.Top, //纵向排布
        horizontalAlignment = Alignment.CenterHorizontally //横向排布
    ) {
        when (text) {
            "全部好友" -> {
                Image(
                    painter = painterResource(id = avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .size(55.dp)
                        .clickable(onClick = {}),
                )
            }
            else -> {
                if (text == userViewModel.uiState.value.visitItem.value.userName) {
                    Surface(
                        shape = CircleShape,
                        border = BorderStroke(2.dp, Green5)
                    ) {
                        Image(
                            painter = painterResource(id = avatar),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .alpha(0.3f)
                                .clickable(onClick = {}),
                        )
                    }
                } else {
                    Image(
                        painter = painterResource(id = avatar),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable(onClick = {}),
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))
            }

        }
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.W600, //设置字体粗细
                fontSize = 12.sp,
            ),
        )


    }
}
