package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.jetpacktest02.IslandDeliver
import com.example.jetpacktest02.IslandVisitOther
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IslandVisitMeScreen(

    //导航函数
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},
    res: Int?,//用户头像
    name: String?,//用户名字
    navController: NavController,
    userViewModel: UserViewModel
) {
    var webView: WebView
    var showLoadingProgress by remember {
        mutableStateOf(true)
    }
    //植物状态显示变量
    var showPlantState by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = showLoadingProgress) {
        delay(12000)
        showLoadingProgress = false
    }
    val loadProgress = remember {
        mutableStateOf(0f)
    }
    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Green1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("animations/plantloading.json"))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = 1f,
        restartOnPlay = true  // 暂停后重新播放是否从头开始
    )

    //SnackBar状态变量
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Surface(modifier = Modifier.fillMaxSize()) {
        //页面布局组件
        Scaffold(
            snackbarHost = {
                androidx.compose.material3.SnackbarHost(snackbarHostState) { data ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        androidx.compose.material3.Snackbar(
                            modifier = Modifier.width(300.dp),
                            snackbarData = data,
                            containerColor = LightGreen,
                            contentColor = Color.White,
                            shape = RoundedCornerShape(30.dp),
                        )
                    }

                }
            },
            //顶部菜单栏
            topBar = {
                TopAppBar(title = {
                    //顶部用户名、头像
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = -20.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        res?.let { painterResource(id = it) }?.let {
                            Image(
                                painter = it,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                        if (name != null) {
                            Text(
                                text = name,
                                style = TextStyle(
                                    fontWeight = FontWeight.W600, //设置字体粗细
                                    fontSize = 18.sp,
                                ),
                            )
                        }
                    }
                },
                    backgroundColor = Green1,
                    contentColor = Color.Black,
                    elevation = 0.dp, //设置阴影
                    //左侧按钮
                    navigationIcon = {
                        //左箭头 返回按钮
                        IconButton(onClick = nav01) {
                            Icon(
                                bitmap = ImageBitmap.imageResource(id = R.drawable.g1_2_0_ic_arrow_left),
                                contentDescription = "",
                            )
                        }
                    },
                    //右侧按钮
                    actions = {

                    }

                )
            }
        ) {
            //绘制背景渐变色
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Green1,
                                Green2
                            )
                        )
                    )
            ) {
                Box(modifier = Modifier.fillMaxSize())
                {

                    //webView 3D植物展示
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
//                        LinearProgressIndicator(
//                            progress = loadProgress.value / 100,
//                            modifier = Modifier.fillMaxWidth(),
//                            color = LightGreen
//                        )

                        AndroidView(factory = { context ->
                            webView = WebView(context)
//                            webView.clearCache(true)
                            webView.setVerticalScrollBarEnabled(false);
                            webView.setHorizontalScrollBarEnabled(false);

                            webView.apply {
                                settings.domStorageEnabled = true
                                settings.javaScriptEnabled = true
                                webViewClient = object : WebViewClient() {}
//                                loadUrl("https://my.spline.design/-07468d271e6d991e66a8f68035dea85b/") //仙人球链接
                                loadUrl("https://my.spline.design/-5cc97696ab5d97403db1ba1f59eaf94d/") //向白魁链接

                            }

                        }, modifier = Modifier
                            .fillMaxSize(), update = { webView ->
                            webView.webChromeClient = object : WebChromeClient() {
                                override fun onProgressChanged(
                                    view: WebView?,
                                    newProgress: Int
                                ) {
                                    loadProgress.value = newProgress.toFloat()
                                    super.onProgressChanged(view, newProgress)
                                }
                            }
                        })

                    }


                    //页面其余组件
                    if (!showLoadingProgress) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            //植物信息卡片
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 15.dp, vertical = 20.dp),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                when (showPlantState) {
                                    true -> {
                                        Box(contentAlignment = Alignment.Center) {
                                            Column(horizontalAlignment = Alignment.End) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.g4_5_shouqi),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .width(35.dp)
                                                        .clickable { showPlantState = false }
                                                )
                                                Spacer(modifier = Modifier.height(5.dp))
                                                Image(
                                                    painter = painterResource(id = R.drawable.g4_5_icbg_plantstates),
                                                    contentDescription = null,
                                                    modifier = Modifier.width(100.dp)
                                                )

                                            }

                                            Text(
                                                text = "123g",
                                                style = TextStyle(
                                                    fontWeight = FontWeight.W900, //设置字体粗细
                                                    fontSize = 11.sp,
                                                    color = LightGreen
                                                ),
                                                modifier = Modifier.offset(14.dp, -34.dp)//向左偏移一段距离
                                            )
                                            Text(
                                                text = "724g",
                                                style = TextStyle(
                                                    fontWeight = FontWeight.W900, //设置字体粗细
                                                    fontSize = 11.sp,
                                                    color = LightGreen
                                                ),
                                                modifier = Modifier.offset(14.dp, 22.dp)//向左偏移一段距离
                                            )
                                            Text(
                                                text = "455g",
                                                style = TextStyle(
                                                    fontWeight = FontWeight.W900, //设置字体粗细
                                                    fontSize = 11.sp,
                                                    color = LightGreen
                                                ),
                                                modifier = Modifier.offset(14.dp, 82.dp)//向左偏移一段距离
                                            )
                                        }
                                    }
                                    false -> {

                                        Box(contentAlignment = Alignment.Center) {
                                            Column(horizontalAlignment = Alignment.End) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.g4_5_ic_zhankai),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .width(35.dp)
                                                        .clickable { showPlantState = true }
                                                )
                                                Spacer(modifier = Modifier.height(5.dp))
                                                Image(
                                                    painter = painterResource(id = R.drawable.g4_5_icbg_plantstates),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .width(100.dp)
                                                        .alpha(0f)
                                                )

                                            }

                                            Text(
                                                text = "123g",
                                                style = TextStyle(
                                                    fontWeight = FontWeight.W900, //设置字体粗细
                                                    fontSize = 11.sp,
                                                    color = LightGreen
                                                ),
                                                modifier = Modifier
                                                    .offset(14.dp, -34.dp)
                                                    .alpha(0f)
                                            )
                                            Text(
                                                text = "724g",
                                                style = TextStyle(
                                                    fontWeight = FontWeight.W900, //设置字体粗细
                                                    fontSize = 11.sp,
                                                    color = LightGreen
                                                ),
                                                modifier = Modifier
                                                    .offset(14.dp, 22.dp)
                                                    .alpha(0f)
                                            )
                                            Text(
                                                text = "455g",
                                                style = TextStyle(
                                                    fontWeight = FontWeight.W900, //设置字体粗细
                                                    fontSize = 11.sp,
                                                    color = LightGreen
                                                ),
                                                modifier = Modifier
                                                    .offset(14.dp, 82.dp)
                                                    .alpha(0f)
                                            )
                                        }
                                    }
                                }


                            }

                            Spacer(
                                modifier = Modifier
                                    .height(300.dp)
                                    .fillMaxWidth()
                            )

//                        when (showPlantState) {
//                            false -> {}
//                            true -> {}
//                        }

                            //交互提示：手指触摸与植物交互
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp), horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.g4_5_interative_prompt),
                                    contentDescription = null,
                                    modifier = Modifier.width(220.dp)
                                )
                            }

//                            // 下方互动按钮组
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(15.dp), horizontalArrangement = Arrangement.Center
//                            ) {
//                                Box(modifier = Modifier.fillMaxWidth()) {
//                                    Image(
//                                        painter = painterResource(id = R.drawable.g4_5_icbg_bottombtns),
//                                        contentDescription = null,
//                                        modifier = Modifier.width(450.dp)
//                                    )
//                                    Row(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .offset(y = 20.dp),
//                                        horizontalArrangement = Arrangement.Center,
//                                        verticalAlignment = Alignment.CenterVertically,
//                                    ) {
//                                        Image(
//                                            painter = painterResource(id = R.drawable.g4_5_btn_clap),
//                                            contentDescription = null,
//                                            modifier = Modifier
//                                                .size(75.dp)
//                                                .clickable(
//                                                    onClick = {
//                                                        scope.launch {
//                                                            snackbarHostState.showSnackbar(
//                                                                "你拍了拍${name}的植物~"
//                                                            )
//                                                        }
//                                                    },
//                                                    indication = null,
//                                                    interactionSource = MutableInteractionSource()
//                                                )
//                                        )
//                                        Spacer(modifier = Modifier.width(20.dp))
//                                        Image(
//                                            painter = painterResource(id = R.drawable.g4_5_btn_sendmessage),
//                                            contentDescription = null,
//                                            modifier = Modifier
//                                                .size(75.dp)
//                                                .clickable(
//                                                    onClick = {
//
//                                                        if (name != null) {
//                                                            userViewModel.uiState.value.visitItem.value.userName =
//                                                                name
//                                                        }
//                                                        if (res != null) {
//                                                            userViewModel.uiState.value.visitItem.value.userAvatar =
//                                                                res
//                                                        }
//                                                        userViewModel.uiState.value.deliverPageMode.value = 0 //切换为消息模式
//
//                                                        navController.navigate(IslandDeliver.route) {
//                                                            launchSingleTop = true; popUpTo(
//                                                            IslandVisitOther.route
//                                                        ) {}
//                                                        }
//
//                                                    },
//                                                    indication = null,
//                                                    interactionSource = MutableInteractionSource()
//                                                )
//                                        )
//                                        Spacer(modifier = Modifier.width(20.dp))
//                                        Image(
//                                            painter = painterResource(id = R.drawable.g4_5_btn_sendphoto),
//                                            contentDescription = null,
//                                            modifier = Modifier
//                                                .size(75.dp)
//                                                .clickable(
//                                                    onClick = {
//
//                                                        if (name != null) {
//                                                            userViewModel.uiState.value.visitItem.value.userName =
//                                                                name
//                                                        }
//                                                        if (res != null) {
//                                                            userViewModel.uiState.value.visitItem.value.userAvatar =
//                                                                res
//                                                        }
//                                                        userViewModel.uiState.value.deliverPageMode.value = 1 //切换为消息模式
//
//                                                        navController.navigate(IslandDeliver.route) {
//                                                            launchSingleTop = true; popUpTo(
//                                                            IslandVisitOther.route
//                                                        ) {}
//                                                        }
//
//                                                    },
//                                                    indication = null,
//                                                    interactionSource = MutableInteractionSource()
//                                                )
//                                        )
//                                    }
//                                }
//
//                            }

                            //看看TA的展柜
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .offset(y = -10.dp), horizontalArrangement = Arrangement.Center
//                            ) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.g4_5_ic_goto_gallery),
//                                    contentDescription = null,
//                                    modifier = Modifier.width(220.dp)
//                                )
//                            }

                        }
                    } else {
                    }


//                    if (loadProgress.value / 100 >= 1.0f) {
//                    } else {
//                        Column(
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .background(
//                                    brush = Brush.verticalGradient(
//                                        colors = listOf(
//                                            Color.Black,
//                                            Color.Black
//                                        )
//                                    ),
//                                    alpha = 0.34f
//                                )
//                        ) {
//                            //加载进度条
//                            CircularProgressIndicator(
//                                progress = loadProgress.value / 100,
//                                modifier = Modifier.offset(y = 250.dp),
//                                color = LightGreen
//                            )
//
//                        }
//                    }
                }
            }

            if (showLoadingProgress) {
                Dialog(onDismissRequest = ({})) {
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.White), contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            LottieAnimation(
                                composition = composition,
                                progress = { progress },
                                modifier = Modifier.size(150.dp)
                            )
                            Text(
                                text = "正在飞速加载中~",
                                style = TextStyle(fontSize = 12.sp),
                                fontWeight = FontWeight.W600
                            )
                        }


                    }
                }

            }
        }
    }
}

