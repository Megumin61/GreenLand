/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetpacktest02.ui.main

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.TapListItemModel
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay


/**
 * The Bills screen.
 */

//viewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
@OptIn(
    ExperimentalPagerApi::class, ExperimentalComposeUiApi::class,
    ExperimentalAnimationApi::class
)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PlantUnchosenScreen(
    nav01: () -> Unit = {},//计划
    nav02: () -> Unit = {},//背包
    nav03: () -> Unit = {},//选择种子
    userViewModel: UserViewModel,
    navController: NavController,


    ) {
    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Color.White, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )
    var state by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = state) {
        state = true
    }
//    MainPlantUnchosenPage(userViewModel, nav01, nav02,nav04=nav03)
    Image(
        painter = painterResource(id = R.drawable.plant_unchosen_bg),
        contentDescription = null,
        modifier = Modifier
            .scale(1.2f, 1.2f)
            .fillMaxHeight()
            .fillMaxSize(),
    )
    AnimatedVisibility(
        visible = state && userViewModel.uiState.value.isGrowUp.value == 0,
        enter = scaleIn(transformOrigin = TransformOrigin(0.5f, 0.5f)) +
                fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
        exit = scaleOut(transformOrigin = TransformOrigin(0.5f, 0.5f)) +
                fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
    ) {
        Image(
            painter = painterResource(id = R.drawable.plant_unchosen_pot),
            contentDescription = null,
            modifier = Modifier
                .size(280.dp, 280.dp)
                .offset(-25.dp, 60.dp)
                .clickable(
                    onClick = nav03,
                    indication = null,
                    interactionSource = MutableInteractionSource()
                )
        )
    }

    //测试按钮
//    Button(
//        onClick = nav03,
//        contentPadding = ButtonDefaults.ButtonWithIconContentPadding
//    ) {
//        Icon(
//            Icons.Filled.Favorite,
//            contentDescription = "Localized description",
//            modifier = Modifier.size(ButtonDefaults.IconSize)
//        )
//        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//        Text("1.1-Plant")
//    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainPlantUnchosenPage(
    userViewModel: UserViewModel,
    nav01: () -> Unit = {},//计划
    nav02: () -> Unit = {},//背包
    nav03: () -> Unit = {},//背包
    nav04: () -> Unit = {},//返回
) {

    //控制卡片pager的切换
    val pagerState = rememberPagerState()
//    LaunchedEffect(pagerState) {
//        snapshotFlow { pagerState.currentPage }.collect { page ->
//            pagerState.animateScrollToPage(page)
//        }
//    }

    //动画状态
    var state by remember {
        mutableStateOf(false)
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

    var isExpand by remember {
        mutableStateOf(true)
    }


    LaunchedEffect(key1 = state) {
        state = true
    }
    LaunchedEffect(key1 = state1) {
        delay(250)
        state1 = true
    }
    LaunchedEffect(key1 = state2) {
        delay(500)
        state2 = true
    }
    LaunchedEffect(key1 = state3) {
        delay(750)
        state3 = true
    }


    Image(
        painter = painterResource(id = R.drawable.g1_1_bg_plant),
        contentDescription = null,
        modifier = Modifier
            .scale(1.1f, 1.0f)
            .fillMaxHeight()
            .fillMaxSize(),
    )
    Column() {
        Image(
            painter = painterResource(id = R.drawable.g1_1_ic_ar),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.End)
                .offset(-20.dp, 20.dp)
                .width(90.dp)
                .height(50.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.g3_1_ic_arrow),
            contentDescription = null,
            modifier = Modifier
                .height(20.dp)
                .scale(2f)
                .offset(35.dp, 100.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.offset(0.dp, 30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.g3_1_btn_addpot),
                    contentDescription = null,
                    modifier = Modifier
                        .offset(0.dp, 0.dp)
                        .width(70.dp)
                        .height(70.dp)
                        .clickable(
                            onClick = nav04,
                            indication = null,
                            interactionSource = MutableInteractionSource()
                        )
                )
                AnimatedVisibility(
                    visible = state && userViewModel.uiState.value.isGrowUp.value == 0,
                    enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                            fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
                    exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                            fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                ) {
                    HorizontalPager(
                        count = 5,
                        state = pagerState,
                        modifier = Modifier
                            .width(150.dp)
                            .clickable(onClick = nav04)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.g3_1_img_pot2),
                            contentDescription = null,
                            modifier = Modifier
                                .width(160.dp)
                                .height(200.dp)
                                .align(Alignment.CenterHorizontally)
                                .offset(20.dp, 0.dp)
                                .clickable(
                                    onClick = nav04,
                                    indication = null,
                                    interactionSource = MutableInteractionSource()
                                )
                        )
                        Image(
                            painter = painterResource(id = R.drawable.g3_1_img_shadow),
                            contentDescription = null,
                            modifier = Modifier
                                .width(120.dp)
                                .height(200.dp)
                                .offset(30.dp, 80.dp)
                                .clickable(
                                    onClick = {
//                                    userViewModel.uiState.value.isGrowUp.value = 1
//                                    userViewModel.uiState.value.plantExp.value = 90
                                    },
                                    indication = null,
                                    interactionSource = MutableInteractionSource()
                                )
                        )

                    }
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .offset(63.dp, 180.dp)
                            .padding(16.dp),
                        activeColor = Green5
                    )
                }


                Text(
                    text = "选择本周的植物吧！",
                    color = Gray5,
                    fontSize = 16.sp,
                    fontWeight = W700,
                    modifier = Modifier.offset(28.dp, 15.dp)
                )
                Text(
                    text = "更换花盆",
                    color = BlueGray4,
                    fontSize = 14.sp,
                    fontWeight = W500,
                    modifier = Modifier.offset(20.dp, 20.dp)
                )


            }
            Column(
                modifier = Modifier
                    .width(110.dp)
                    .padding(10.dp),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(100.dp),
                    modifier = Modifier
                        .height(180.dp)
                        .width(80.dp)
                        .offset(10.dp, 20.dp)

                ) {
                    Column(
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.g1_1_ic_plan),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                                .clickable(
                                    onClick = nav01,
                                    indication = null,
                                    interactionSource = MutableInteractionSource()
                                )
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Image(
                            painter = painterResource(id = R.drawable.g1_1_ic_bag),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                                .clickable(
                                    onClick = nav02,
                                    indication = null,
                                    interactionSource = MutableInteractionSource()
                                )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(60.dp))
            }
        }

        //心情、水分、能量
        Spacer(modifier = Modifier.height(160.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.offset(0.dp, -30.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(20.dp, 20.dp, 20.dp, 20.dp)
                    .fillMaxHeight()
            ) {
//                Spacer(modifier = Modifier.height(10.dp))
                AnimatedVisibility(
                    visible = state1,
                    enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                            fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
                    exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                            fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.g3_1_icbg_msg),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.End)
                            .offset(15.dp, 0.dp)
                            .width(160.dp)
                            .scale(1.2f)
                    )
                }


            }
            //消息
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp)
            ) {
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(60.dp),
                    border = BorderStroke(width = 1.dp, color = BlueGray5),
                    modifier = Modifier.wrapContentWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.g1_1_ic_message_point),
                        contentDescription = null,
                        modifier = Modifier
                            .height(6.dp)
                            .offset(30.dp, 7.dp)

                    )
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Image(
                            painter = painterResource(id = R.drawable.g1_1_ic_msg),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .offset(0.dp, 4.dp)
                                .clickable(
                                    onClick = {
                                        isExpand = !isExpand
                                    }, indication = null,
                                    interactionSource = MutableInteractionSource()
                                )
                        )
                        AnimatedVisibility(isExpand) {
                            Text(
                                text = "消息",
                                color = Green700,
                                fontSize = 14.sp,
                                fontWeight = W900,
                                modifier = Modifier.offset(0.dp, 8.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
//                Image(
//                    painter = painterResource(id = R.drawable.g1_1_ic_message),
//                    contentDescription = null,
//                    modifier = Modifier.height(50.dp)
//
//                )
                Spacer(modifier = Modifier.height(10.dp))
                AnimatedVisibility(
                    visible = !isExpand,
                    enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                            fadeIn(initialAlpha = 0.5f) + expandIn(expandFrom = Alignment.TopStart),
                    exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                            fadeOut() + shrinkOut(shrinkTowards = Alignment.TopEnd)
                ) {
                    Box() {
                        Image(
                            painter = painterResource(id = R.drawable.g1_1icbg_message),
                            contentDescription = null,
                            modifier = Modifier.height(170.dp)
                        )
                        LazyColumn(
                            modifier = Modifier
                                .height(160.dp)
                                .padding(8.dp)
                        ) {
//                        userViewModel.uiState.value.tabMessageList.forEach {listItemModel->
//                            PlantMsgItem(listItemModel)
//
//                        }
                            items(userViewModel.uiState.value.tabMessageList) { item ->
                                PlantMsgItem(item)
                                Spacer(modifier = Modifier.height(2.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantUnchosenMsgItem(listItemModel: TapListItemModel) {
    Row(Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = listItemModel.res),
            contentDescription = null,
            modifier = Modifier
                .width(28.dp)
                .height(28.dp)
                .border(width = 1.dp, color = LightGreen, shape = RoundedCornerShape(15.dp))
//                    .clickable(onClick = nav02)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Column() {
            Text(
                text = listItemModel.name,
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
            )
            Text(
                listItemModel.msg,
                fontSize = 10.sp,
                style = MaterialTheme.typography.bodySmall,
                color = Gray1,
                textAlign = TextAlign.Justify
            )
        }
    }
}


