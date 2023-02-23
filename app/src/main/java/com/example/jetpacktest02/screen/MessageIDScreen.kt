package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpacktest02.AppIntroduction
import com.example.jetpacktest02.Message
import com.example.jetpacktest02.MessageFriend
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageIDScreen(navController: NavHostController) {
    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Green1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
//    ScaffoldPage()
    Scaffold(topBar = {
        androidx.compose.material.TopAppBar(title = {
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
            backgroundColor = Green1,
            contentColor = Color.Black,
            elevation = 0.dp, //设置阴影
            //左侧按钮
            navigationIcon = {
                androidx.compose.material.IconButton(onClick = {
                    navController.navigate(
                        Message.route
                    ) {
                        launchSingleTop = true;
                    }
                }) {
                    androidx.compose.material.Icon(
                        bitmap = ImageBitmap.imageResource(id = R.drawable.g1_2_0_ic_arrow_left),
                        contentDescription = "",
                    )
                }
            },
            //右侧按钮
            actions = {
            }

        )
    },
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Box(
                    modifier = Modifier
                        .offset(0.dp, -50.dp)
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Snackbar(
                        modifier = Modifier
                            .width(280.dp)
                            .wrapContentHeight(),
                        containerColor = Green5,
                        contentColor = Color.White,
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Text(data.visuals.message)
                    }
                }
            }
        },
//        floatingActionButton = {
//            var clickCount by remember { mutableStateOf(0) }
//            ExtendedFloatingActionButton(
//                onClick = {
//                    // show snackbar as a suspend function
//                    scope.launch {
//                        snackbarHostState.showSnackbar(
//                            "Snackbar # ${++clickCount}"
//                        )
//                    }
//                }
//            ) { Text("Show snackbar", modifier = Modifier.align(Alignment.CenterVertically)) }
//        },
        content = { innerPadding ->
            Text(
                text = "GreenLand ScaffoldPage",
                modifier = Modifier
                    .padding(innerPadding)
//                    .fillMaxSize()
//                    .align(Alignment.Center)
                    .wrapContentSize(),
                textAlign = TextAlign.Center
            )
            Surface(modifier = Modifier.fillMaxSize()) {

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
                        .padding(20.dp)
                ) {
                    Spacer(modifier = Modifier.height(80.dp))
                    Image(
                        painter = painterResource(id = R.drawable.g2_6_img_code),
                        contentDescription = null,
                        modifier = Modifier.size(350.dp)
                    )
                    Spacer(modifier = Modifier.height(100.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.g2_6_btn_scan),
                            contentDescription = null,
                            modifier = Modifier.size(90.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.g2_6_btn_save),
                            contentDescription = null,
                            modifier = Modifier
                                .size(90.dp)
                                .clickable(
                                    onClick = {
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                "保存照片成功",
                                                duration = SnackbarDuration.Short
                                            )
                                            navController.navigate(MessageFriend.route) {
                                                launchSingleTop = true;
                                            }
                                        }
                                    }, indication = null,
                                    interactionSource = MutableInteractionSource()
                                )
                        )
                    }
                }
            }
        }
    )
}
