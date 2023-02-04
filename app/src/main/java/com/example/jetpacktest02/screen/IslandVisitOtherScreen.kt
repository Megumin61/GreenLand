package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IslandVisitOtherScreen(

    //导航函数
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},
) {
    var webView: WebView

    //植物状态显示变量
    var showPlantState by remember {
        mutableStateOf(false)
    }

    val loadProgress = remember {
        mutableStateOf(0f)
    }
    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Green1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )

    //引入webview

    Surface(modifier = Modifier.fillMaxSize()) {
        //页面布局组件
        Scaffold(
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
                        Image(
                            painter = painterResource(id = R.drawable.g4_3_avatar1),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(
                            text = "skccc",
                            style = TextStyle(
                                fontWeight = FontWeight.W600, //设置字体粗细
                                fontSize = 18.sp,
                            ),
                        )
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
                            webView.clearCache(true)
                            webView.setVerticalScrollBarEnabled(false);
                            webView.setHorizontalScrollBarEnabled(false);

                            webView.apply {
                                settings.domStorageEnabled = false
                                settings.javaScriptEnabled = true
                                webViewClient = object : WebViewClient() {}
                                loadUrl("https://my.spline.design/-b295baacf9372fa88aca6d2633179e3c/")
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
                    if(loadProgress.value/100>=1.0f){
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
                                    .height(170.dp)
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

                            // 下方互动按钮组
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp), horizontalArrangement = Arrangement.Center
                            ) {
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    Image(
                                        painter = painterResource(id = R.drawable.g4_5_icbg_bottombtns),
                                        contentDescription = null,
                                        modifier = Modifier.width(450.dp)
                                    )
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .offset(y = 20.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.g4_5_btn_clap),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(75.dp)
                                                .clickable { }
                                        )
                                        Spacer(modifier = Modifier.width(20.dp))
                                        Image(
                                            painter = painterResource(id = R.drawable.g4_5_btn_sendmessage),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(75.dp)
                                                .clickable { }
                                        )
                                        Spacer(modifier = Modifier.width(20.dp))
                                        Image(
                                            painter = painterResource(id = R.drawable.g4_5_btn_sendphoto),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(75.dp)
                                                .clickable { }
                                        )
                                    }
                                }

                            }

                            //看看TA的展柜
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .offset(y = -10.dp), horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.g4_5_ic_goto_gallery),
                                    contentDescription = null,
                                    modifier = Modifier.width(220.dp)
                                )
                            }

                        }
                    }else{}



                    if(loadProgress.value/100>=1.0f){
                    }else{
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Black,
                                            Color.Black
                                        )
                                    ),
                                    alpha = 0.34f
                                )
                        ) {
                            //加载进度条
                            CircularProgressIndicator(progress = loadProgress.value / 100, modifier = Modifier.offset(y = 250.dp),color= LightGreen)

                        }
                    }


                }

            }
        }
    }
}

