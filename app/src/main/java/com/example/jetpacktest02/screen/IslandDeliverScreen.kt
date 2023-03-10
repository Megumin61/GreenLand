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
    //???????????????????????????
    rememberSystemUiController().setStatusBarColor(
        Flesh1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )

    Surface(modifier = Modifier.fillMaxSize()) {
        //?????????????????????
        val permissionState =
            rememberPermissionState(permission = "android.permission.CAMERA")

        //???????????????????????????:0 / ????????????:1???
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

        //???????????????
        var text by remember { mutableStateOf("") }

        //SnackBar????????????
        val snackbarHostState = remember { SnackbarHostState() }

        //???????????? ????????????
        val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()

        //???????????????
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
                                fontWeight = FontWeight.W900, //??????????????????
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-35.dp, 0.dp)//????????????????????????
                        )
                    }
                },
                    backgroundColor = Flesh1,
                    contentColor = Color.Black,
                    elevation = 0.dp, //????????????
                    //????????????
                    navigationIcon = {

                        IconButton(onClick = nav01) {
                            Icon(
                                bitmap = ImageBitmap.imageResource(id = R.drawable.g1_2_0_ic_arrow_left),
                                contentDescription = "",
                            )
                        }
                    },
                    //????????????
                    actions = { }

                )
            }
        ) {

            //??????????????????
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
                //????????????????????????Col
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 40.dp, vertical = 40.dp)
                ) {
                    //????????????
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
                                //??????????????????
                                .clickable {
                                    permissionState.launchPermissionRequest()
                                    if (userViewModel.uiState.value.deliverPageMode.value== 0) {
                                        userViewModel.uiState.value.deliverPageMode.value = 1 //?????????????????????
                                    } else {
                                        userViewModel.uiState.value.deliverPageMode.value = 0
                                    }
                                },
                        )
                    }

                    //??????????????????
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp), horizontalArrangement = Arrangement.End
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp)
                                .padding(13.dp) // ?????????
                            ,
                            backgroundColor = Color.White,
                            shape = MaterialTheme.shapes.large,
                            elevation = 1.5.dp

                        ) {
                            when (userViewModel.uiState.value.deliverPageMode.value) {
                                0 -> {
                                    //????????????
                                    Column(
                                        modifier = Modifier.padding(
                                            start = 14.dp,
                                            end = 14.dp,
                                            top = 20.dp,
                                            bottom = 35.dp
                                        )
                                    ) {
                                        //???????????????
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

                                    //????????????
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(14.dp),
                                        verticalArrangement = Arrangement.Bottom
                                    ) {
                                        //??????????????????
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

                    //????????????
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .offset(deliverBtnOffset),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //???????????????
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
                                                    "????????????~"
                                                )
                                            }
                                            text = ""
                                            //??????visitItem??????
                                            userViewModel.uiState.value.visitItem.value = ExploreMemberItem()
                                        },
                                        indication = null,
                                        interactionSource = MutableInteractionSource()
                                    ),
                            )
                        }
                        //???????????????
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

                    //???????????????????????????
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

                    //?????????????????? ??????
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

            //?????????????????? ????????????????????????????????????????????????
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
                                        text = "??????????????????",
                                        style = TextStyle(
                                            fontWeight = FontWeight.W900, //??????????????????
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
                            //???????????????
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp),
                                horizontalArrangement = Arrangement.Start
                            ) {

                                AvatarItem(
                                    "????????????",
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

                            //???????????????

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
            //?????????????????????????????????????????????????????????????????????
            BackHandler(
                enabled = (state.currentValue == ModalBottomSheetValue.HalfExpanded
                        || state.currentValue == ModalBottomSheetValue.Expanded),
                onBack = {
                    scope.launch {
                        state.hide()

                    }
                }
            )

            //?????????????????????
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
//                                //????????????????????????
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
    //??????+???????????????
    Column(
        modifier = Modifier.width(76.dp),
        verticalArrangement = Arrangement.Top, //????????????
        horizontalAlignment = Alignment.CenterHorizontally //????????????
    ) {
        when (text) {
            "????????????" -> {
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
                fontWeight = FontWeight.W600, //??????????????????
                fontSize = 12.sp,
            ),
        )


    }
}
