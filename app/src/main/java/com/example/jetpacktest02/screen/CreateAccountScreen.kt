package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpacktest02.*
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.MarsViewModel
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import okhttp3.internal.notifyAll

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(
    navController: NavController,
    userViewModel: UserViewModel,
    marsViewModel: MarsViewModel
) {
    var username by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var userId by remember {
        mutableStateOf(0)
    }
    rememberSystemUiController().setStatusBarColor(
        Flesh2, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Flesh2,
                        Flesh1
                    )
                )
            ),
        verticalArrangement = Arrangement.Top,
    ) {
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
        LaunchedEffect(key1 = state1) {
            delay(0)
            state1 = true
        }
        LaunchedEffect(key1 = state2) {
            delay(0)
            state2 = true
        }
        AnimatedVisibility(
            visible = state1,
            enter = fadeIn(initialAlpha = 0.3f) + slideInVertically(
                initialOffsetY = { 800 },
                animationSpec = tween(durationMillis = 1200)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                //头像
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                        .padding(top = 40.dp, bottom = 10.dp)
                ) {

                    Row(horizontalArrangement = Arrangement.Center) {
                        Text(text = "首先选择一个心仪的头像~", style = TextStyle(color = Gray1))
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        HorizontalPager(
                            count = 10,
                            state = pagerState,
                            modifier = Modifier
                        ) { page ->
                            when (page) {
                                0 -> {
                                    Image(
                                        painter = painterResource(id = R.drawable.userprofile_12),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(120.dp),
                                    )
                                }
                                1 -> {
                                    Image(
                                        painter = painterResource(id = R.drawable.userprofile_1),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(120.dp),
                                    )

                                }
                                2 -> {
                                    Image(
                                        painter = painterResource(id = R.drawable.userprofile_13),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(120.dp),
                                    )
                                }
                                3 -> {
                                    Image(
                                        painter = painterResource(id = R.drawable.userprofile_4),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(120.dp),
                                    )
                                }
                                4 -> {
                                    Image(
                                        painter = painterResource(id = R.drawable.userprofile_3),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(120.dp),
                                    )
                                }
                                5 -> {
                                    Image(
                                        painter = painterResource(id = R.drawable.userprofile_6),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(120.dp),
                                    )
                                }
                                6 -> {
                                    Image(
                                        painter = painterResource(id = R.drawable.userprofile_2),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(120.dp),
                                    )
                                }
                                7 -> {
                                    Image(
                                        painter = painterResource(id = R.drawable.userprofile_17),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(120.dp),
                                    )
                                }
                                8 -> {
                                    Image(
                                        painter = painterResource(id = R.drawable.userprofile_18),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(120.dp),
                                    )
                                }
                                9 -> {
                                    Image(
                                        painter = painterResource(id = R.drawable.userprofile_19),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(120.dp),
                                    )
                                }
                            }
                        }
                    }

                    Row(horizontalArrangement = Arrangement.Center) {
                        HorizontalPagerIndicator(
                            pagerState = pagerState,
                            modifier = Modifier
                                .padding(16.dp),
                            activeColor = Green5
                        )
                    }
                    Spacer(modifier = Modifier.height(70.dp))
                    Row(horizontalArrangement = Arrangement.Center) {
                        Text(text = "创建你的专属昵称~", style = TextStyle(color = Gray1))
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    //用户名
                    Row(horizontalArrangement = Arrangement.Center) {
                        TextField(
                            value = username,
                            onValueChange = {
                                username = it
                                if (it.length > 0) {
                                    //抓取用户列表数据，用于获取用户id
                                    userViewModel.uiState.value.userList =
                                        marsViewModel.getUserList().toMutableList()
                                }
                            },
                            textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
                            singleLine = true,
                            placeholder = {
                            },

                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Green5,
                                unfocusedBorderColor = Green8, focusedBorderColor = Green5,
                                cursorColor = Green5
                            ),
                            modifier = Modifier.width(200.dp)
                        )
                    }


                }



                Row(horizontalArrangement = Arrangement.Center) {
                    //创建按钮
                    Button(
                        onClick = {
                            navController.navigate(PlantUnchosen.route) { launchSingleTop = true; }
                            when (pagerState.currentPage) {
                                0 -> {
                                    userViewModel.uiState.value.meItem.value.userAvatar =
                                        R.drawable.userprofile_12
                                }
                                1 -> {
                                    userViewModel.uiState.value.meItem.value.userAvatar =
                                        R.drawable.userprofile_1
                                }
                                2 -> {
                                    userViewModel.uiState.value.meItem.value.userAvatar =
                                        R.drawable.userprofile_13
                                }
                                3 -> {
                                    userViewModel.uiState.value.meItem.value.userAvatar =
                                        R.drawable.userprofile_4
                                }
                                4 -> {
                                    userViewModel.uiState.value.meItem.value.userAvatar =
                                        R.drawable.userprofile_3
                                }
                                5 -> {
                                    userViewModel.uiState.value.meItem.value.userAvatar =
                                        R.drawable.userprofile_6
                                }
                                6 -> {
                                    userViewModel.uiState.value.meItem.value.userAvatar =
                                        R.drawable.userprofile_2
                                }
                                7 -> {
                                    userViewModel.uiState.value.meItem.value.userAvatar =
                                        R.drawable.userprofile_17
                                }
                                8 -> {
                                    userViewModel.uiState.value.meItem.value.userAvatar =
                                        R.drawable.userprofile_18
                                }
                                9 -> {
                                    userViewModel.uiState.value.meItem.value.userAvatar =
                                        R.drawable.userprofile_19
                                }

                            }


                            //获得刚刚注册的用户id
                            if (userViewModel.uiState.value.userList.size > 1) {
                                userId =
                                    userViewModel.uiState.value.userList[userViewModel.uiState.value.userList.size - 1].id
                                Log.i("code", "id: ${userId.toString()}")
                            }
                            marsViewModel.updateName(userId, username)
                            userViewModel.uiState.value.meItem.value.userName = username
                            navController.navigate(Plant.route) { launchSingleTop = true; }
                        },
                        shape = RoundedCornerShape(27.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GreenMain,
                            contentColor = GreenMain
                        ),
                        modifier = Modifier
                            .size(width = 200.dp, height = 50.dp)
                    ) {
                        Text(text = "开启绿岛之旅", color = Color.White, fontSize = 20.sp)
                    }
                }

            }
        }

    }
}