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
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jetpacktest02.R
import com.example.jetpacktest02.SharePost
import com.example.jetpacktest02.ViewModel.NotificationTestViewModel

import com.example.jetpacktest02.ViewModel.TapListItemModel
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.screen.FriendList
import com.example.jetpacktest02.screen.GIFimage
import com.example.jetpacktest02.screen.IconButtonFriendList
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.format.TextStyle


/**
 * The Bills screen.
 */

//viewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
@OptIn(ExperimentalPagerApi::class, ExperimentalComposeUiApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PlantScreen(
    nav01: () -> Unit = {},//??????
    nav02: () -> Unit = {},//??????
    userViewModel: UserViewModel,
    navController: NavController,


    ) {

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("animations/trophy.json"))
    var showHealthSumCard by remember {
        mutableStateOf(false)
    }
    var HealthSumCardAnim by remember {
        mutableStateOf(false)
    }

    val showSumCard: () -> Unit = {
        showHealthSumCard = true
    }
    LaunchedEffect(key1 = showHealthSumCard) {
//        delay(300)
//        showHealthSumCard = true
        if (showHealthSumCard == true) {
            delay(300)
            HealthSumCardAnim = true
        }
    }
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = 1,
        isPlaying = HealthSumCardAnim,
        speed = 0.8f,
        restartOnPlay = true  // ???????????????????????????????????????
    )

    //???????????????????????????
    rememberSystemUiController().setStatusBarColor(
        Color.White, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )
    //pagerState?????????viewpager??????
    val pagerState: PagerState = remember { PagerState() }
    VerticalPager(count = 2, state = pagerState) { page ->
        userViewModel.uiState.value.PlantPage.value = page
        if (page == 0) {
            MainPlantPage(userViewModel, nav01,nav02, showSumCard)
        } else if (page == 1) {
//            SecondPlantPage(userViewModel.uiState.value.PlantPage.value)
            SecondPlantPage(userViewModel.uiState.value.PlantPage.value)
        }
    }

    if (showHealthSumCard) {
        Dialog(
            onDismissRequest = { showHealthSumCard = false },
            properties = DialogProperties(usePlatformDefaultWidth = true)
        ) {
//            HealthSumCard()

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                androidx.compose.material3.Card(
//                    onClick = { /* Do something */ },
                    modifier = Modifier.size(width = 380.dp, height = 450.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp, vertical = 0.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        AnimatedVisibility(
                            visible = HealthSumCardAnim,
                            enter = fadeIn(initialAlpha = 0.3f) + slideInVertically(
                                initialOffsetY = { 400 },
                                animationSpec = tween(durationMillis = 500)
                            )
                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.g0_1_ic_report_cup),
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .size(300.dp)
//                                    .offset(0.dp, -15.dp)
//                            )
                            LottieAnimation(
                                composition = composition,
                                progress = { progress },
                                modifier = Modifier
                                    .size(250.dp)
                                    .offset(0.dp, -35.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .offset(y = -50.dp)
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            AnimatedVisibility(
                                visible = HealthSumCardAnim,
                                enter = fadeIn(
                                    initialAlpha = 0f,
                                    animationSpec = tween(durationMillis = 800)
                                ) + slideInVertically(
                                    initialOffsetY = { 20 },
                                    animationSpec = tween(durationMillis = 1200)
                                )
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {

                                    Row(
                                        Modifier
                                            .fillMaxWidth()
//                                    .padding()
//                                    .offset(0.dp, -50.dp)
                                        ,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = "????????????",
                                            fontSize = 15.sp,
                                            color = Gray7
                                        )
                                        Text(
                                            text = "97%",
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.W900,
                                            color = Gray7, modifier = Modifier
                                                .offset(0.dp, -8.dp)
                                                .padding(horizontal = 3.dp)

                                        )
                                        Text(
                                            text = "??????????????????",
                                            fontSize = 15.sp,
                                            color = Gray7
                                        )

                                    }

                                    Text(
                                        text = "?????????????????????????????????????????????",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.W900,
                                        color = Gray7, modifier = Modifier
//                                    .offset(0.dp, -45.dp)
                                            .padding(start = 3.dp, bottom = 45.dp, end = 3.dp)

                                    )
                                }
                            }



                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .offset(0.dp, -30.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.g0_1_ic_report_flower),
                                    contentDescription = null
                                )
                                var progress by remember { mutableStateOf(0.1f) }
                                val animatedProgress by animateFloatAsState(
                                    targetValue = progress,
                                    animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
                                )


                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    ProgressBar(
                                        modifier = Modifier
                                            .width(120.dp) // ?????????????????????
                                            .height(10.dp)
                                            .offset(5.dp, 7.dp), // ?????????????????????
                                        progress = 0.8f,
                                        color = Color(26, 207, 163),
                                        cornerRadius = 12.dp,
                                        backgroundColor = ProgressGray
                                    )


                                    Row(modifier = Modifier.offset(0.dp, 12.dp)) {
                                        Text(
                                            text = "94",
                                            fontSize = 12.sp,
                                            color = LightGreen

                                        )
                                        Text(
                                            text = "/100",
                                            fontSize = 12.sp,
                                            color = Color.Black
                                        )


                                    }
                                }


                                Text(
                                    text = "??????+",
                                    fontSize = 13.sp,
                                    color = LightGreen,
                                    fontWeight = FontWeight.W900,
                                    modifier = Modifier.offset(5.dp, 1.dp)

                                )
                                Text(
                                    text = "17",
                                    fontSize = 14.sp,
                                    color = LightGreen,
                                    fontWeight = FontWeight.W900,
                                    modifier = Modifier.offset(-4.dp, 0.5.dp)
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.g0_1_ic_report_lighting),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .offset(-8.dp, 5.dp)
                                        .size(15.dp)
                                )

                            }


                            AnimatedVisibility(
                                visible = HealthSumCardAnim,
                                enter = fadeIn(initialAlpha = 0.3f, animationSpec = tween(400)),
                                modifier = Modifier.height(60.dp)
                            ) {

                                androidx.compose.material3.Button(
                                    onClick = {
                                        showHealthSumCard = false
                                        navController.navigate(SharePost.route) {
                                            launchSingleTop = true;
                                        }
                                    },
                                    modifier = Modifier
                                        .width(136.dp)
                                        .height(54.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Green5)
                                ) {
                                    Text(
                                        text = "????????????",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.W900
                                    )
                                }
                            }
                        }


                    }
                }
                Spacer(modifier = Modifier.height(0.dp))

                //????????????
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(top = 25.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.g4_6_1_ic_cancel),
                        contentDescription = null,
                        modifier = Modifier
                            .width(23.dp)
                            .clickable(
                                onClick = {
                                    showHealthSumCard = false
                                },
                                indication = null,
                                interactionSource = MutableInteractionSource()
                            )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainPlantPage(
    userViewModel: UserViewModel,
    nav01: () -> Unit = {},//??????
    nav02: () -> Unit = {},//??????
    showSumCard: () -> Unit = {},//??????????????????
) {
    //????????????
    val feelingValue: Int = 21
    val energyValue: Int = 32
    var allEnergy by remember { mutableStateOf(330) }
    val exp by animateFloatAsState(
        targetValue = userViewModel.uiState.value.plantExp.value.toFloat(),
        animationSpec = FloatTweenSpec(duration = 1500)
    )

    //lotties
    val composition2 by rememberLottieComposition(LottieCompositionSpec.Asset("animations/SgyPw7n8jf.json"))
    var showFireball by remember {
        mutableStateOf(false)
    }
    val progress2 by animateLottieCompositionAsState(
        composition2,
        iterations = 1,
        isPlaying = showFireball,
        speed = 0.5f,
        restartOnPlay = true  // ???????????????????????????????????????
    )

    //????????????
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
            painter = painterResource(id = R.drawable.g1_1_ic_arrow_down),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(20.dp)
                .offset(0.dp, 680.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.g1_1_ic_ar),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.End)
                .offset(-20.dp, 0.dp)
                .width(90.dp)
                .height(50.dp)
        )
//        Image(
//            painter = painterResource(id = R.drawable.g1_1_ic_ar),
//            contentDescription = null,
//            modifier = Modifier
//                .align(Alignment.Start)
//                .offset(0.dp, -20.dp)
//                .width(90.dp)
//                .height(50.dp)
//        )
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
                modifier = Modifier.offset(5.dp, 0.dp)
            ) {
                Text(
                    "${userViewModel.uiState.value.meItem.value.userName}????????????",
                    color = Gray5,
                    fontSize = 20.sp,
                    fontWeight = W900,
                    modifier = Modifier.offset(0.dp, (-20).dp)
                )

                Box {

                    //????????????
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(20.dp)
//                            .background(Color.Red)
                            .clickable(
                                onClick = showSumCard,
                                indication = null,
                                interactionSource = MutableInteractionSource()
                            )
                            .offset(x = 100.dp)
                            .padding(bottom = 100.dp, start = 100.dp)

                    )

                    ProgressBar(
                        modifier = Modifier
                            .height(8.dp)
                            .width(130.dp),
                        progress = exp / 100,
                        color = LightGreen,
                        cornerRadius = 10.dp,
                        backgroundColor = Color.White
                    )
                    Text(
                        text = userViewModel.uiState.value.plantExp.value.toString(),
                        color = LightGreen,
                        fontSize = 14.sp,
                        fontWeight = W900,
                        modifier = Modifier.offset(90.dp, 10.dp)
                    )
                    Text(
                        "/100",
                        color = Gray5,
                        fontSize = 14.sp,
                        fontWeight = W900,
                        modifier = Modifier.offset(110.dp, 10.dp)
                    )
                }
//                Image(
//                    painter = painterResource(id = R.drawable.g1_1_img_plant_experience),
//                    contentDescription = null,
//                )

                AnimatedVisibility(
                    visible = state && userViewModel.uiState.value.isGrowUp.value == 1,
                    enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                            fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
                    exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                            fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                ) {
                    GIFimage(gif = R.drawable.gif_04, modifier = Modifier
                        .width(180.dp)
                        .height(300.dp))
//                    Image(
//                        painter = painterResource(id = R.drawable.g1_1_img_flower),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .width(180.dp)
//                            .height(300.dp)
//                    )
                }

                AnimatedVisibility(
                    visible = state && userViewModel.uiState.value.isGrowUp.value == 0,
                    enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                            fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
                    exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                            fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                ) {
                    GIFimage(gif = R.drawable.gif_06, modifier = Modifier
                        .width(140.dp)
                        .height(300.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable(
                            onClick = {
                                userViewModel.uiState.value.isGrowUp.value = 1
                                userViewModel.uiState.value.plantExp.value = 90
                                allEnergy = 350
                                showFireball = true

                            }, indication = null,
                            interactionSource = MutableInteractionSource()
                        ))
//                    Image(
//                        painter = painterResource(id = R.drawable.g1_1_img_plant_seedling),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .width(120.dp)
//                            .height(300.dp)
//                            .align(Alignment.CenterHorizontally)
//                            .clickable(
//                                onClick = {
//                                    userViewModel.uiState.value.isGrowUp.value = 1
//                                    userViewModel.uiState.value.plantExp.value = 90
//                                    allEnergy = 350
//                                    showFireball = true
//
//                                }, indication = null,
//                                interactionSource = MutableInteractionSource()
//                            )
//                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.g1_1_bg_plantstage),
                    contentDescription = null,
                    modifier = Modifier
                        .height(50.dp)
                        .offset(0.dp, -70.dp)
                )
                Text(
                    text = if (userViewModel.uiState.value.isGrowUp.value == 0) "?????????" else "?????????",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = W900,
                    modifier = Modifier.offset(0.dp, -107.dp)
                )
            }
            Spacer(modifier = Modifier.width(0.dp))
            Column(
                modifier = Modifier
                    .width(120.dp)
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
                        .offset(10.dp, 0.dp)

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
                Box(modifier = Modifier.align(Alignment.Start)) {
                    Image(
                        painter = painterResource(id = R.drawable.g1_1_ic_energy),
                        contentDescription = null,
                        modifier = Modifier.scale(1.2f)
                    )
                    AnimatedContent(targetState = allEnergy,
                        transitionSpec = {
                            if (targetState > initialState) {
                                slideInVertically { height -> height } + fadeIn() with
                                        slideOutVertically { height -> -height } + fadeOut()
                            } else {
                                slideInVertically { height -> -height } + fadeIn() with
                                        slideOutVertically { height -> height } + fadeOut()
                            }.using(
                                SizeTransform(clip = false)
                            )
                        }
                    ) { allEnergy ->
//                        Text(text = "Count: $targetCount")
                        Text(
                            text = allEnergy.toInt().toString(),
                            fontWeight = W500,
                            color = Green9,
                            modifier = Modifier.offset(72.dp, 12.dp)
                        )
                    }
                }
            }
        }

        //????????????????????????

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.offset(0.dp, -30.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(20.dp, 0.dp, 20.dp, 0.dp)
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
                    Box(modifier = Modifier.align(Alignment.Start)) {
                        Image(
                            painter = painterResource(id = R.drawable.g1_1_ic_feeling),
                            contentDescription = null,
                            modifier = Modifier
                                .width(130.dp)
                        )
                        Text(
                            text = feelingValue.toString() + "g",
                            fontWeight = W900,
                            color = BlueGray3,
                            modifier = Modifier.offset(70.dp, 30.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                AnimatedVisibility(
                    visible = state2,
                    enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                            fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
                    exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                            fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                ) {
                    Box(modifier = Modifier.align(Alignment.Start)) {
                        Image(
                            painter = painterResource(id = R.drawable.g1_1_ic_water),
                            contentDescription = null,
                            modifier = Modifier
                                .width(130.dp)
                        )
                        Text(
                            text = userViewModel.uiState.value.waterValue.toString() + "g",
                            fontWeight = W900,
                            color = BlueGray3,
                            modifier = Modifier.offset(70.dp, 30.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                AnimatedVisibility(
                    visible = state3,
                    enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                            fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
                    exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                            fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                ) {
                    Box(modifier = Modifier.align(Alignment.Start)) {
                        Image(
                            painter = painterResource(id = R.drawable.g1_1_ic_huoli),
                            contentDescription = null,
                            modifier = Modifier
                                .width(130.dp)
                        )
                        Text(
                            text = energyValue.toString() + "g",
                            fontWeight = W900,
                            color = BlueGray3,
                            modifier = Modifier.offset(70.dp, 30.dp)
                        )
                    }
                }
            }
            //??????
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
                                .height(40.dp)
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
                                text = "??????",
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
    AnimatedVisibility(
        visible = showFireball,
        enter = fadeIn(initialAlpha = 0.3f) + slideInVertically(
            initialOffsetY = { 0 },
            animationSpec = tween(durationMillis = 0)
        )
    ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.g0_1_ic_report_cup),
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .size(300.dp)
//                                    .offset(0.dp, -15.dp)
//                            )
        LottieAnimation(
            composition = composition2,
            progress = { progress2 },
            modifier = Modifier
                .size(280.dp)
                .offset(-30.dp, 140.dp)
        )
    }
}

//@Preview
//@OptIn(ExperimentalAnimationApi::class)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SecondPlantPage(page: Int, viewModel: NotificationTestViewModel = viewModel()) {
    val context = LocalContext.current
    val waterTime: Int = 3
    val step: Int = 332
    val sitTime: String = "6:31:20"
    Image(
        painter = painterResource(id = R.drawable.g1_3_bg),
        contentDescription = null,
        modifier = Modifier
            .scale(1.1f, 1.0f)
            .fillMaxHeight()
            .fillMaxSize()
    )

    Column(Modifier.padding(30.dp)) {
        var state2 by remember {
            mutableStateOf(false)
        }
        if (page == 1) {
            LaunchedEffect(key1 = state2) {
                delay(2000)
                state2 = true
            }
        }

        val heightPre by animateFloatAsState(
            targetValue = if (state2) 1f else 0f,
            animationSpec = FloatTweenSpec(duration = 1500)
        )

        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            AnimatedVisibility(
                visible = state2,
                enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeIn(initialAlpha = 0.5f) + expandIn(expandFrom = Alignment.TopStart),
                exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
            ) {
                Box() {
                    Image(
                        painter = painterResource(id = R.drawable.g1_3_ic_watercount),
                        contentDescription = null,
                        modifier = Modifier
                            .height(180.dp)
                            .clickable(onClick = {
                                viewModel.showNotification(context, "?????????", "???????????????????????????")
                            }, indication = null, interactionSource = MutableInteractionSource())
                    )
                    Text(
                        text = waterTime.toString() + "???",
                        color = Blue1,
                        fontSize = 16.sp,
                        fontWeight = W900,
                        modifier = Modifier.offset(90.dp, 13.dp)
                    )
                }
            }
            AnimatedVisibility(
                visible = state2,
                enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeIn(initialAlpha = 0.5f) + expandIn(expandFrom = Alignment.TopStart),
                exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
            ) {
                Box() {
                    Image(
                        painter = painterResource(id = R.drawable.g1_3_ic_dailywalk),
                        contentDescription = null,
                        modifier = Modifier.height(180.dp)
                    )
                    Text(
                        text = step.toString() + "???",
                        color = Red2,
                        fontSize = 16.sp,
                        fontWeight = W900,
                        modifier = Modifier.offset(88.dp, 12.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column() {
            AnimatedVisibility(
                visible = state2,
                enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeIn(initialAlpha = 0.5f) + expandIn(expandFrom = Alignment.TopStart),
                exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
            ) {
                Box() {
                    Image(
                        painter = painterResource(id = R.drawable.g1_3_bg_longsittime),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = sitTime,
                        color = BlueGray4,
                        fontSize = 20.sp,
                        fontWeight = W500,
                        modifier = Modifier.offset(22.dp, 48.dp)
                    )
                    Text(
                        text = "20%",
                        color = Red2,
                        fontSize = 14.sp,
                        fontWeight = W500,
                        modifier = Modifier.offset(100.dp, 54.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.g1_3_img_up),
                        contentDescription = null,
                        modifier = Modifier
                            .size(16.dp)
                            .offset(130.dp, 56.dp)
                    )
                    Row(
                        Modifier
                            .width((heightPre * 250).dp)
                            .offset(20.dp, 120.dp)
                    ) {
                        Surface(
                            color = Red3, modifier = Modifier
                                .weight(0.39f)
                                .height(20.dp)
                        ) {
                        }
                        Surface(
                            color = Red4, modifier = Modifier
                                .weight(0.38f)
                                .height(20.dp)
                        ) {
                        }
                        Surface(
                            color = Green10, modifier = Modifier
                                .weight(0.23f)
                                .height(20.dp)
                        ) {
                        }
                    }
                    Text(
                        text = "2:32:53",
                        color = Color.Black,
                        fontSize = 13.sp,
                        fontWeight = W500,
                        modifier = Modifier.offset(25.dp, 168.dp)
                    )
                    Text(
                        text = "2:23:17",
                        color = Color.Black,
                        fontSize = 13.sp,
                        fontWeight = W500,
                        modifier = Modifier.offset(110.dp, 168.dp)
                    )
                    Text(
                        text = "1:23:43",
                        color = Color.Black,
                        fontSize = 13.sp,
                        fontWeight = W500,
                        modifier = Modifier.offset(200.dp, 168.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            AnimatedVisibility(
                visible = state2,
                enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeIn(initialAlpha = 0.5f) + expandIn(expandFrom = Alignment.TopStart),
                exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.g1_3_bg_eattime),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    }
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantMsgItem(listItemModel: TapListItemModel) {
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

@Composable
fun NotificationTest(viewModel: NotificationTestViewModel = viewModel()) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            viewModel.showNotification(context, "?????????", "???????????????????????????")
        }) {
            Text(text = "?????????????????????")
        }
    }
}

