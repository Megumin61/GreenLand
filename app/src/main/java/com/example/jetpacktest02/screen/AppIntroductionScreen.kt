package com.example.jetpacktest02.screen

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.jetpacktest02.CreateAccount
import com.example.jetpacktest02.LoginFront
import com.example.jetpacktest02.R
import com.example.jetpacktest02.compose.GIFimage
import com.example.jetpacktest02.ui.main.CardPage
import com.example.jetpacktest02.ui.main.EatCardPage
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun AppIntroductionScreen(navController: NavController) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("animations/plantloading.json"))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = 1f,
        restartOnPlay = true  // 暂停后重新播放是否从头开始
    )

    //控制卡片pager的切换
    val pagerState = rememberPagerState()
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            pagerState.animateScrollToPage(page)
        }
    }
    var state1 by remember {
        mutableStateOf(false)
    }
    var state2 by remember {
        mutableStateOf(false)
    }
    var state3 by remember {
        mutableStateOf(false)
    }
    if (pagerState.currentPage == 0) {
        LaunchedEffect(key1 = state1) {
            delay(0)
            state1 = true
        }
    }
    if (pagerState.currentPage == 1) {
        LaunchedEffect(key1 = state2) {
            delay(0)
            state2 = true
        }
    }
    if (pagerState.currentPage == 2) {
        LaunchedEffect(key1 = state3) {
            delay(0)
            state3 = true
        }
    }

    rememberSystemUiController().setStatusBarColor(
        Green1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )
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
            ),
        verticalArrangement = Arrangement.Top,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 0.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 100.dp)
                    .fillMaxWidth()
                    .height(400.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalPager(
                        count = 3,
                        state = pagerState,
                        modifier = Modifier
                    ) { page ->
                        when (page) {
                            0 -> {
                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    AnimatedVisibility(
                                        visible = state1,
                                        enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                                                fadeIn(initialAlpha = 0.5f) + expandIn(expandFrom = Alignment.TopStart),
                                        exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                                                fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                                    ) {
                                        Box(
                                            modifier = Modifier,
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.introduction1),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .height(200.dp)
                                                    .clickable(
                                                        enabled = true,
                                                        onClick = {},
                                                        indication = null,
                                                        interactionSource = MutableInteractionSource()
                                                    ),
                                            )
                                        }
                                    }


                                    Spacer(modifier = Modifier.height(20.dp))
                                    AnimatedVisibility(
                                        visible = state1,
                                        enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                                                fadeIn(initialAlpha = 0.5f) + expandIn(expandFrom = Alignment.TopStart),
                                        exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                                                fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 40.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Spacer(modifier = Modifier.height(20.dp))
                                            Text(
                                                text = "创新社交玩法",
                                                textAlign = TextAlign.Center,
                                                style = TextStyle(fontSize = 25.sp)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                            Text(
                                                text = "双岛社交，查看好友或扫描陌生人状况，进行趣味交互关心",
                                                textAlign = TextAlign.Center,
                                                style = TextStyle(fontSize = 18.sp, color = Text3Gray)
                                            )

                                        }


                                    }

//                                    Text(
//                                        text = "we guided you through the greatest concepts so you can grasp their main ideas at a glance",
//                                        textAlign = TextAlign.Center
//                                    )
                                }
                            }
                            1 -> {
                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    AnimatedVisibility(
                                        visible = state2,
                                        enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                                                fadeIn(initialAlpha = 0.5f) + expandIn(expandFrom = Alignment.TopStart),
                                        exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                                                fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                                    ) {
                                        Box(
                                            modifier = Modifier,
                                            contentAlignment = Alignment.Center
                                        ) {
                                            GIFimage(
                                                gif = R.drawable.introduction2, modifier = Modifier
                                                    .height(250.dp)
                                                    .clickable(
                                                        enabled = true,
                                                        onClick = {},
                                                        indication = null,
                                                        interactionSource = MutableInteractionSource()
                                                    )
                                            )

                                        }
                                    }


                                    Spacer(modifier = Modifier.height(20.dp))
                                    AnimatedVisibility(
                                        visible = state2,
                                        enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                                                fadeIn(initialAlpha = 0.5f) + expandIn(expandFrom = Alignment.TopStart),
                                        exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                                                fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 40.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Spacer(modifier = Modifier.height(20.dp))
                                            Text(
                                                text = "虚拟植物形象",
                                                textAlign = TextAlign.Center,
                                                style = TextStyle(fontSize = 25.sp)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                            Text(
                                                text = "每周选定植物体验养成，与个人健康绑定",
                                                textAlign = TextAlign.Center,
                                                style = TextStyle(fontSize = 18.sp, color = Text3Gray)
                                            )

                                        }

                                    }

//                                    Text(
//                                        text = "we guided you through the greatest concepts so you can grasp their main ideas at a glance",
//                                        textAlign = TextAlign.Center
//                                    )
                                }
                            }
                            2 -> {
                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    AnimatedVisibility(
                                        visible = state3,
                                        enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                                                fadeIn(initialAlpha = 0.5f) + expandIn(expandFrom = Alignment.TopStart),
                                        exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                                                fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(350.dp, 180.dp)
                                                .clip(RoundedCornerShape(15.dp))
//                                            .background(
//                                                Color(1f, 1f, 1f, 0.3f)
//                                            )
                                            ,
                                            contentAlignment = Alignment.Center
                                        ) {
                                            GIFimage(
                                                gif = R.drawable.gif_03,
                                                modifier = Modifier
                                                    .height(140.dp)
                                                    .offset(x = -110.dp)
                                            )
                                            GIFimage(
                                                gif = R.drawable.gif_04,
                                                modifier = Modifier.height(140.dp)
                                            )
                                            GIFimage(
                                                gif = R.drawable.gif_02,
                                                modifier = Modifier
                                                    .height(140.dp)
                                                    .offset(x = 110.dp)
                                            )


                                        }
                                    }

                                    Spacer(modifier = Modifier.height(20.dp))
                                    AnimatedVisibility(
                                        visible = state3,
                                        enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                                                fadeIn(initialAlpha = 0.5f) + expandIn(expandFrom = Alignment.TopStart),
                                        exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                                                fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 40.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Spacer(modifier = Modifier.height(20.dp))
                                            Text(
                                                text = "个性化植物",
                                                textAlign = TextAlign.Center,
                                                style = TextStyle(fontSize = 25.sp)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                            Text(
                                                text = "diy植物形象，每个用户都可以拥有不一样的“自己”",
                                                textAlign = TextAlign.Center,
                                                style = TextStyle(fontSize = 18.sp, color = Text3Gray)
                                            )

                                        }
                                    }

//                                    Text(
//                                        text = "we guided you through the greatest concepts so you can grasp their main ideas at a glance",
//                                        textAlign = TextAlign.Center
//                                    )
                                }
                            }

                        }
                    }
                }
            }

            Column(
                modifier = Modifier.padding(bottom = 10.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp),
                    activeColor = Green5
                )

                Button(
                    onClick = {
                        navController.navigate(CreateAccount.route) {
                            launchSingleTop = true;
                        }
                    },
                    shape = RoundedCornerShape(27.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 160.dp, height = 50.dp)
                ) {
                    Text(text = "开始探索 !", color = Color.White, fontSize = 20.sp)
                }

                Row(
                    modifier = Modifier.padding(bottom = 0.dp, top = 50.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .height(40.dp)
                    )
                }
            }
        }
    }
}