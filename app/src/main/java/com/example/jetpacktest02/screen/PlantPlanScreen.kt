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
import android.media.Image
import android.os.Build
import android.widget.Space
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.airbnb.lottie.compose.*
import com.airbnb.lottie.model.content.CircleShape
import com.example.jetpacktest02.PlanList
import com.example.jetpacktest02.R
import com.example.jetpacktest02.screen.AvatarItem
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.color.KalendarThemeColor
import com.himanshoe.kalendar.model.KalendarEvent
import com.himanshoe.kalendar.model.KalendarType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlanBottomSheet(nav05: () -> Unit = {}) {

    Column() {
        androidx.compose.material.ListItem(
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.padding(vertical = 15.dp))
                    Text(
                        text = "修改计划",
                        style = TextStyle(
                            fontWeight = FontWeight.W900, //设置字体粗细
                            fontSize = 16.sp,
                        ),
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.padding(vertical = 15.dp))
                    Text(
                        text = "添加计划",
                        style = TextStyle(
                            fontWeight = FontWeight.W900, //设置字体粗细
                            fontSize = 16.sp,
                        ),
                        modifier = Modifier.clickable(onClick = nav05)
                    )
                    Spacer(modifier = Modifier.padding(vertical = 15.dp))
                    Text(
                        text = "删除计划",
                        style = TextStyle(
                            fontWeight = FontWeight.W900, //设置字体粗细
                            fontSize = 16.sp,
                            color = Gray1
                        ),
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.padding(vertical = 15.dp))
                }

            }
        )
    }
}


@Composable
fun PlanItem(@DrawableRes iconRes: Int) {
    Image(
        painter = painterResource(id = iconRes), contentDescription = null,
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
    )
}



@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class, ExperimentalComposeUiApi::class,
    ExperimentalAnimationApi::class
)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

/*部分布局样式,CardPage放在里面*/
@Composable
fun NewScreen() {


    var openCalendar by remember {
        mutableStateOf(false)
    }
    //控制卡片pager的切换
    val pagerState = rememberPagerState()
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            pagerState.animateScrollToPage(page)
        }
    }

    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.g1_4_1_bg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize(), contentScale = ContentScale.FillWidth
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row() {
                    Icon(
                            bitmap = ImageBitmap.imageResource(id = R.drawable.g1_2_0_ic_arrow_left),
                            contentDescription = null,
                            modifier = Modifier.offset(-110.dp, 5.dp)
                            )
                    Text(
                        text = "计划日程",
                        style = TextStyle(
                            fontWeight = FontWeight.W900, //设置字体粗细
                            fontSize = 18.sp
                        ), modifier = Modifier.offset(-100.dp, 1.dp)
                    )
                    Icon(
                            bitmap = ImageBitmap.imageResource(id = R.drawable.g1_4_1_ic_more),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(110.dp, 1.dp)
                                .size(32.dp)
                                .clickable(
                                    onClick = { scope.launch { state.show() } },
                                    indication = null,
                                    interactionSource = MutableInteractionSource()
                                )
                        )
                }

            }
        }
        var change by remember{ mutableStateOf(false) }
        val flowerSize by animateDpAsState(
            targetValue = if(change) 320.dp else 300.dp
        )

        /*LaunchedEffect(key1 = change) {
            delay(1000)
            change = true}*/

        if(flowerSize == 320.dp) {
            change = false
        }
        var state1 by remember {
            mutableStateOf(false)
        }
        LaunchedEffect(key1 = state1){
            delay(250)
            state1 = true
        }
        AnimatedVisibility(
            visible = state1,
            enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                    fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.Center),
            exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                    fadeOut() + shrinkOut(shrinkTowards = Alignment.Center)
        ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(vertical = 140.dp), verticalArrangement = Arrangement.Top) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

                com.example.jetpacktest02.screen.GIFimage(
                    modifier = Modifier
                        .clickable(
                            onClick = { change = true },
                            indication = null,
                            interactionSource = MutableInteractionSource()
                        )
                        .size(flowerSize)
                        .offset(0.dp, -60.dp)
                        .fillMaxWidth(),
                    gif = R.drawable.gif_04
                )
            }}}
        //添加动画


        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            var state by remember {
                mutableStateOf(false)
            }
            LaunchedEffect(key1 = state) {
                delay(100)
                state = true
            }
            AnimatedVisibility(
                visible = state,
                enter =slideInVertically(initialOffsetY = { -40 }
                ) + expandVertically(
                    expandFrom = Alignment.Top
                ) + fadeIn(initialAlpha = 0.3f),
                exit= fadeOut(targetAlpha = 0f) + shrinkVertically(shrinkTowards = Alignment.Top)
            ){
            Box(modifier = Modifier.fillMaxWidth(), Alignment.BottomCenter) {
                HorizontalPager(count = 4, state = pagerState) { page ->
                    when (page) {
                        0 -> {
                            CardPage(
                                planname = "运动",
                                aimcontent = "目标步数",
                                realcontent = "实际步数",
                                aimnum = 3000,
                                realnum = 2786,
                                { openCalendar = true }
                            )
                        }
                        1 -> {
                            CardPage(
                                planname = "喝水",
                                aimcontent = "目标次数",
                                realcontent = "实际次数",
                                aimnum = 25,
                                realnum = 18,
                                { openCalendar = true }
                            )
                        }
                        2 -> {
                            CardPage(
                                planname = "睡眠",
                                aimcontent = "目标次数",
                                realcontent = "实际次数",
                                aimnum = 25,
                                realnum = 18,
                                { openCalendar = true }
                            )
                        }
                        3 -> {
                            EatCardPage(
                                planname = "吃饭",
                                breakfastPic=R.drawable.g1_1_ic_eatcard_green,
                                breakfastState="8:00",
                                lunchPic=R.drawable.g1_1_ic_eatcard_red,
                                lunchState="缺餐",
                                dinnerPic=R.drawable.g1_1_ic_eatcard_gray,
                                dinnerState="",
                                openCalendar = { openCalendar = true }
                            )
                        }
                    }
                }
                /* ViewPager*/


            }}
        }
    }
    //日历弹窗组件
    if (openCalendar) {

        Dialog(
            onDismissRequest = { openCalendar=false},

        ) {
            Kalendar(
                kalendarType = KalendarType.Firey, kalendarThemeColors = listOf(
                    KalendarThemeColor(
                        Color(0xffEAFEFD),
                        Color(0XFFBBDED8),
                        Color(0xFF8BABA8)
                    ),
                    KalendarThemeColor(
                        Color(0xffEAFEFD),
                        Color(0XFFBBDED8),
                        Color(0xFF8BABA8)
                    ),
                    KalendarThemeColor(
                        Color(0xffEAFEFD),
                        Color(0XFFBBDED8),
                        Color(0xFF8BABA8)
                    ),
                    KalendarThemeColor(
                        Color(0xffEAFEFD),
                        Color(0XFFBBDED8),
                        Color(0xFF8BABA8)
                    ),
                    KalendarThemeColor(
                        Color(0xffEAFEFD),
                        Color(0XFFBBDED8),
                        Color(0xFF8BABA8)
                    ),
                    KalendarThemeColor(
                        Color(0xffEAFEFD),
                        Color(0XFFBBDED8),
                        Color(0xFF8BABA8)
                    ),
                    KalendarThemeColor(
                        Color(0xffEAFEFD),
                        Color(0XFFBBDED8),
                        Color(0xFF8BABA8)
                    ),
                    KalendarThemeColor(
                        Color(0xffEAFEFD),
                        Color(0XFFBBDED8),
                        Color(0xFF8BABA8)
                    ),
                    KalendarThemeColor(
                        Color(0xffEAFEFD),
                        Color(0XFFBBDED8),
                        Color(0xFF8BABA8)
                    ),
                    KalendarThemeColor(
                        Color(0xffEAFEFD),
                        Color(0XFFBBDED8),
                        Color(0xFF8BABA8)
                    ),
                    KalendarThemeColor(
                        Color(0xffEAFEFD),
                        Color(0XFFBBDED8),
                        Color(0xFF8BABA8)
                    ),
                    KalendarThemeColor(
                        Color(0xffEAFEFD),
                        Color(0XFFBBDED8),
                        Color(0xFF8BABA8)
                    ),
                ),
                kalendarEvents = listOf(
                    KalendarEvent(LocalDate(2023, 2, 5), "Birthday"),
                    KalendarEvent(LocalDate(2023, 2, 5), "Birthday"),
                    KalendarEvent(LocalDate(2023, 2, 5), "Birthday"),
                    KalendarEvent(LocalDate(2023, 2, 23), "Birthday"),
                    KalendarEvent(LocalDate(2023, 2, 23), "Party"),
                    KalendarEvent(LocalDate(2023, 2, 23), "Club"),
                ),
                modifier = Modifier.clip(RoundedCornerShape(15.dp))
            )
            Row(modifier = Modifier
                .offset(y = 0.dp)
                .fillMaxWidth()
                .padding(10.dp), horizontalArrangement = Arrangement.End) {
                Image(
                    painter = painterResource(id = R.drawable.g4_6_1_ic_cancel),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(onClick = { openCalendar = false })                )
            }

        }
    }



    ModalBottomSheetLayout(
        sheetState = state,
        sheetShape = MaterialTheme.shapes.large,
        sheetContent = {
            PlanBottomSheet()

        }
    ) {
    }

    //处理”返回键“事件，当抽屉展开时，返回键触发“关闭抽屉”
    BackHandler(
        enabled = (state.currentValue == ModalBottomSheetValue.HalfExpanded
                || state.currentValue == ModalBottomSheetValue.Expanded),
        onBack = {
            scope.launch {
                state.hide()
            }
        }
    )
}

/*计划卡片样式,需要加日历和百分数水球*/
@Composable
fun CardPage(
    planname: String,
    aimcontent: String,
    realcontent: String,
    aimnum: Int,
    realnum: Int,
    openCalendar: () -> Unit = {}
) {
    var planProgress by remember {
        mutableStateOf(0.6f)
    }
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("animations/water2.json"))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1,
        isPlaying = true,
        speed = 1f,
        restartOnPlay = true,
        clipSpec = LottieClipSpec.Progress(0f, planProgress)
    )

    Card(
        modifier = Modifier.size(width = 331.dp, height = 410.dp),
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = planname, fontSize = 26.sp,
                    fontWeight = FontWeight.W900,
                    style = MaterialTheme.typography.labelLarge,
                    color = Green4,
                    textAlign = TextAlign.Justify
                )

                Text(
                    text = "每日打卡", fontSize = 12.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray1,
                    textAlign = TextAlign.Justify
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    text = "创建时间：" + "当日日期", fontSize = 12.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray1,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = "已完成打卡" + "1/5", fontSize = 12.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray1,
                    textAlign = TextAlign.Justify
                )

            }
            Spacer(modifier = Modifier.padding(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DayCardItem(23, GreenGray1)
                DayCardItem(24, Green8)
                DayCardItem(25, GreenGray1)
                DayCardItem(26, GreenGray1)
                DayCardItem(27, GreenGray1)
                Text(
                    text = "查看全部日历",
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray1,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(top = 11.dp)
                        .clickable(onClick = openCalendar)
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            androidx.compose.material.Divider(
                color = GreenGray1,
                modifier = Modifier.padding(horizontal = 30.dp)
            )
            Spacer(modifier = Modifier.padding(7.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(start = 30.dp)
                ) {
                    Text(
                        text = aimcontent, fontSize = 14.sp,
                        style = MaterialTheme.typography.bodySmall,
                        color = Gray1,
                        fontWeight = FontWeight.W900,
                        textAlign = TextAlign.Justify, modifier = Modifier.padding(bottom = 0.dp)
                    )

                    Text(
                        text = aimnum.toString(), fontSize = 26.sp,
                        style = MaterialTheme.typography.labelLarge,
                        color = Green5,
                        textAlign = TextAlign.Justify
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(end = 30.dp)
                ) {
                    Text(
                        text = realcontent, fontSize = 14.sp,
                        style = MaterialTheme.typography.bodySmall,
                        color = Gray1,
                        fontWeight = FontWeight.W900,
                        textAlign = TextAlign.Justify, modifier = Modifier.padding(bottom = 0.dp)
                    )
                    Text(
                        text = realnum.toString(), fontSize = 26.sp,
                        style = MaterialTheme.typography.labelLarge,
                        color = Green5,
                        textAlign = TextAlign.Justify
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(), horizontalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier
                            .height(75.dp)
                            .width(200.dp)
                            .clip(CircleShape), contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = "${(planProgress * 100f).roundToInt()}%", fontSize = 15.sp,
                        color = Color(0xff445B60),
                        fontWeight = FontWeight.W700,
                    )
                }
            }
        }
    }

}

@Composable
fun EatCardPage(
    breakfastPic:Int,breakfastState:String,
    lunchPic:Int,lunchState:String,
    dinnerPic:Int,dinnerState:String,
    planname: String,
    openCalendar: () -> Unit = {}
) {
    var planProgress by remember {
        mutableStateOf(0.6f)
    }
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("animations/water2.json"))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1,
        isPlaying = true,
        speed = 1f,
        restartOnPlay = true,
        clipSpec = LottieClipSpec.Progress(0f, planProgress)
    )
    Card(
        modifier = Modifier.size(width = 331.dp, height = 410.dp),
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)) {
                Text(
                    text = "吃饭时间", fontSize = 14.sp,
                    fontWeight = FontWeight.W900,
                    color = Green4,
                    textAlign = TextAlign.Justify
                )
                Box() {
                    androidx.compose.material.Divider(
                        color = GreenGray1,
                        thickness=2.dp,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .offset(0.dp, 8.dp)
                    )
                    Image(painterResource(id = breakfastPic), contentDescription = null, modifier = Modifier.offset(20.dp,6.dp))
                    Image(painterResource(id = lunchPic), contentDescription = null, modifier = Modifier.offset(100.dp,6.dp))
                    Image(painterResource(id = dinnerPic), contentDescription = null, modifier = Modifier.offset(180.dp,6.dp))
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 95.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "早餐", fontSize = 10.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Green4,
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        text = breakfastState, fontSize = 10.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Green4,
                        textAlign = TextAlign.Justify
                    )

                }
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 60.dp)) {
                    Text(
                        text = "午餐", fontSize = 10.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Green4,
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        text = lunchState, fontSize = 10.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Green4,
                        textAlign = TextAlign.Justify
                    )

                }
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.offset(60.dp,0.dp)) {
                    Text(
                        text = "晚餐", fontSize = 10.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Green4,
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        text = dinnerState, fontSize = 10.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Green4,
                        textAlign = TextAlign.Justify
                    )

                }

            }
            Spacer(modifier = Modifier.padding(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = planname, fontSize = 26.sp,
                    fontWeight = FontWeight.W900,
                    style = MaterialTheme.typography.labelLarge,
                    color = Green4,
                    textAlign = TextAlign.Justify
                )

                Text(
                    text = "每日打卡", fontSize = 12.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray1,
                    textAlign = TextAlign.Justify
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    text = "创建时间：" + "当日日期", fontSize = 12.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray1,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = "已完成打卡" + "1/5", fontSize = 12.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray1,
                    textAlign = TextAlign.Justify
                )

            }
            Spacer(modifier = Modifier.padding(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DayCardItem(23, GreenGray1)
                DayCardItem(24, Green8)
                DayCardItem(25, GreenGray1)
                DayCardItem(26, GreenGray1)
                DayCardItem(27, GreenGray1)
                Text(
                    text = "查看全部日历",
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray1,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(top = 11.dp)
                        .clickable(onClick = openCalendar)
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            androidx.compose.material.Divider(
                color = GreenGray1,
                modifier = Modifier.padding(horizontal = 30.dp)
            )
            Spacer(modifier = Modifier.padding(7.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(), horizontalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier
                            .height(75.dp)
                            .width(200.dp)
                            .clip(CircleShape), contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = "${(planProgress * 100f).roundToInt()}%", fontSize = 15.sp,
                        color = Color(0xff445B60),
                        fontWeight = FontWeight.W700,
                    )
                }
            }
        }
    }



}


@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun PlantPlanScreen(
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},
    nav03: () -> Unit = {},
    nav05: () -> Unit = {},
    navController: NavController

    ) {

    Box() {
        rememberSystemUiController().setStatusBarColor(
            Yellow1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
        )

        val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()
        Box() {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row() {

                        Icon(
                            bitmap = ImageBitmap.imageResource(id = R.drawable.g1_2_0_ic_arrow_left),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(-110.dp, 5.dp)
                                .clickable(
                                    onClick = nav03,
                                    indication = null,
                                    interactionSource = MutableInteractionSource()
                                )
                        )

                        Text(
                            text = "计划日程",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //设置字体粗细
                                fontSize = 18.sp
                            ), modifier = Modifier.offset(-117.dp, 5.dp)
                        )
                        Icon(
                            bitmap = ImageBitmap.imageResource(id = R.drawable.g1_4_1_ic_more),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(90.dp, 5.dp)
                                .size(32.dp)
                                .clickable(
                                    onClick = { scope.launch { state.show() } },
                                    indication = null,
                                    interactionSource = MutableInteractionSource()
                                )
                        )
                    }

                }
            }
            Image(
                painter = painterResource(id = R.drawable.g1_1_img_flower),
                contentDescription = null,
                modifier = Modifier
                    .size(290.dp)
                    .offset(50.dp, 130.dp)
            )
        }
        //NewScreen()

        var openCalendar by remember {
            mutableStateOf(false)
        }
        //控制卡片pager的切换
        val pagerState = rememberPagerState()
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                pagerState.animateScrollToPage(page)
            }
        }


        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.g1_4_1_bg),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxSize(), contentScale = ContentScale.FillWidth
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row() {
                        Icon(
                            bitmap = ImageBitmap.imageResource(id = R.drawable.g1_2_0_ic_arrow_left),
                            contentDescription = null,
                            modifier = Modifier.offset(-110.dp, 5.dp)
                        )
                        Text(
                            text = "计划日程",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //设置字体粗细
                                fontSize = 18.sp
                            ), modifier = Modifier.offset(-100.dp, 1.dp)
                        )
                        Icon(
                            bitmap = ImageBitmap.imageResource(id = R.drawable.g1_4_1_ic_more),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(110.dp, 1.dp)
                                .size(32.dp)
                                .clickable(
                                    onClick = { scope.launch { state.show() } },
                                    indication = null,
                                    interactionSource = MutableInteractionSource()
                                )
                        )
                    }

                }
            }
            var change by remember { mutableStateOf(false) }
            val flowerSize by animateDpAsState(
                targetValue = if (change) 320.dp else 300.dp
            )

            /*LaunchedEffect(key1 = change) {
                delay(1000)
                change = true}*/

            if (flowerSize == 320.dp) {
                change = false
            }
            var state1 by remember {
                mutableStateOf(false)
            }
            LaunchedEffect(key1 = state1) {
                delay(250)
                state1 = true
            }
            AnimatedVisibility(
                visible = state1,
                enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.Center),
                exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeOut() + shrinkOut(shrinkTowards = Alignment.Center)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(vertical = 140.dp), verticalArrangement = Arrangement.Top
                ) {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        com.example.jetpacktest02.screen.GIFimage(
                            modifier = Modifier
                                .clickable(
                                    onClick = { change = true },
                                    indication = null,
                                    interactionSource = MutableInteractionSource()
                                )
                                .size(flowerSize)
                                .offset(0.dp, -60.dp)
                                .fillMaxWidth(),
                            gif = R.drawable.gif_04
                        )
                    }
                }
            }
            //添加动画


            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                var state by remember {
                    mutableStateOf(false)
                }
                LaunchedEffect(key1 = state) {
                    delay(100)
                    state = true
                }
                AnimatedVisibility(
                    visible = state,
                    enter = slideInVertically(initialOffsetY = { -40 }
                    ) + expandVertically(
                        expandFrom = Alignment.Top
                    ) + fadeIn(initialAlpha = 0.3f),
                    exit = fadeOut(targetAlpha = 0f) + shrinkVertically(shrinkTowards = Alignment.Top)
                ) {
                    Box(modifier = Modifier.fillMaxWidth(), Alignment.BottomCenter) {
                        HorizontalPager(count = 4, state = pagerState) { page ->
                            when (page) {
                                0 -> {
                                    CardPage(
                                        planname = "运动",
                                        aimcontent = "目标步数",
                                        realcontent = "实际步数",
                                        aimnum = 3000,
                                        realnum = 2786,
                                        { openCalendar = true }
                                    )
                                }
                                1 -> {
                                    CardPage(
                                        planname = "喝水",
                                        aimcontent = "目标次数",
                                        realcontent = "实际次数",
                                        aimnum = 25,
                                        realnum = 18,
                                        { openCalendar = true }
                                    )
                                }
                                2 -> {
                                    CardPage(
                                        planname = "睡眠",
                                        aimcontent = "目标次数",
                                        realcontent = "实际次数",
                                        aimnum = 25,
                                        realnum = 18,
                                        { openCalendar = true }
                                    )
                                }
                                3 -> {
                                    EatCardPage(
                                        planname = "吃饭",
                                        breakfastPic = R.drawable.g1_1_ic_eatcard_green,
                                        breakfastState = "8:00",
                                        lunchPic = R.drawable.g1_1_ic_eatcard_red,
                                        lunchState = "缺餐",
                                        dinnerPic = R.drawable.g1_1_ic_eatcard_gray,
                                        dinnerState = "",
                                        openCalendar = { openCalendar = true }
                                    )
                                }
                            }
                        }
                        /* ViewPager*/


                    }
                }
            }
        }
        //日历弹窗组件
        if (openCalendar) {

            Dialog(
                onDismissRequest = { openCalendar = false },

                ) {
                Kalendar(
                    kalendarType = KalendarType.Firey, kalendarThemeColors = listOf(
                        KalendarThemeColor(
                            Color(0xffEAFEFD),
                            Color(0XFFBBDED8),
                            Color(0xFF8BABA8)
                        ),
                        KalendarThemeColor(
                            Color(0xffEAFEFD),
                            Color(0XFFBBDED8),
                            Color(0xFF8BABA8)
                        ),
                        KalendarThemeColor(
                            Color(0xffEAFEFD),
                            Color(0XFFBBDED8),
                            Color(0xFF8BABA8)
                        ),
                        KalendarThemeColor(
                            Color(0xffEAFEFD),
                            Color(0XFFBBDED8),
                            Color(0xFF8BABA8)
                        ),
                        KalendarThemeColor(
                            Color(0xffEAFEFD),
                            Color(0XFFBBDED8),
                            Color(0xFF8BABA8)
                        ),
                        KalendarThemeColor(
                            Color(0xffEAFEFD),
                            Color(0XFFBBDED8),
                            Color(0xFF8BABA8)
                        ),
                        KalendarThemeColor(
                            Color(0xffEAFEFD),
                            Color(0XFFBBDED8),
                            Color(0xFF8BABA8)
                        ),
                        KalendarThemeColor(
                            Color(0xffEAFEFD),
                            Color(0XFFBBDED8),
                            Color(0xFF8BABA8)
                        ),
                        KalendarThemeColor(
                            Color(0xffEAFEFD),
                            Color(0XFFBBDED8),
                            Color(0xFF8BABA8)
                        ),
                        KalendarThemeColor(
                            Color(0xffEAFEFD),
                            Color(0XFFBBDED8),
                            Color(0xFF8BABA8)
                        ),
                        KalendarThemeColor(
                            Color(0xffEAFEFD),
                            Color(0XFFBBDED8),
                            Color(0xFF8BABA8)
                        ),
                        KalendarThemeColor(
                            Color(0xffEAFEFD),
                            Color(0XFFBBDED8),
                            Color(0xFF8BABA8)
                        ),
                    ),
                    kalendarEvents = listOf(
                        KalendarEvent(LocalDate(2023, 2, 5), "Birthday"),
                        KalendarEvent(LocalDate(2023, 2, 5), "Birthday"),
                        KalendarEvent(LocalDate(2023, 2, 5), "Birthday"),
                        KalendarEvent(LocalDate(2023, 2, 23), "Birthday"),
                        KalendarEvent(LocalDate(2023, 2, 23), "Party"),
                        KalendarEvent(LocalDate(2023, 2, 23), "Club"),
                    ),
                    modifier = Modifier.clip(RoundedCornerShape(15.dp))
                )
                Row(
                    modifier = Modifier
                        .offset(y = 0.dp)
                        .fillMaxWidth()
                        .padding(10.dp), horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.g4_6_1_ic_cancel),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable(onClick = { openCalendar = false })
                    )
                }

            }
        }

        //处理”返回键“事件，当抽屉展开时，返回键触发“关闭抽屉”
        BackHandler(
            enabled = (state.currentValue == ModalBottomSheetValue.HalfExpanded
                    || state.currentValue == ModalBottomSheetValue.Expanded),
            onBack = {
                scope.launch {
                    state.hide()
                }
            }
        )



        ModalBottomSheetLayout(

            sheetState = state,
            sheetShape = MaterialTheme.shapes.large,
            sheetContent = {

                //底部弹窗样式
                Column() {
                    androidx.compose.material.ListItem(
                        text = {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.padding(vertical = 15.dp))
                                Text(
                                    text = "修改计划",
                                    style = TextStyle(
                                        fontWeight = FontWeight.W900, //设置字体粗细
                                        fontSize = 16.sp,
                                    ),
                                    modifier = Modifier
                                )
                                Spacer(modifier = Modifier.padding(vertical = 15.dp))
                                Text(
                                    text = "添加计划",
                                    style = TextStyle(
                                        fontWeight = FontWeight.W900, //设置字体粗细
                                        fontSize = 16.sp,
                                    ),
                                    modifier = Modifier.clickable(onClick = {navController.navigate(
                                        PlanList.route){scope.launch {
                                        state.hide()}
                                    }},indication = null,interactionSource = MutableInteractionSource())
                                )
                                Spacer(modifier = Modifier.padding(vertical = 15.dp))
                                Text(
                                    text = "删除计划",
                                    style = TextStyle(
                                        fontWeight = FontWeight.W900, //设置字体粗细
                                        fontSize = 16.sp,
                                        color = Gray1
                                    ),
                                    modifier = Modifier
                                )
                                Spacer(modifier = Modifier.padding(vertical = 15.dp))
                            }

                        }
                    )
                }


                // 底部弹窗样式


            }
        ) {

        }







        // NewScreen()


//        Column {
//            Text("1.2-plant-plan")
//            Button(
//                onClick = nav01,
//                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
//            ) {
//                Icon(
//                    Icons.Filled.Favorite,
//                    contentDescription = "Localized description",
//                    modifier = Modifier.size(ButtonDefaults.IconSize)
//                )
//                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//                Text("1.1-Plant")
//            }
//            Button(
//                onClick = nav02,
//                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
//            ) {
//                Icon(
//                    Icons.Filled.Favorite,
//                    contentDescription = "Localized description",
//                    modifier = Modifier.size(ButtonDefaults.IconSize)
//                )
//                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//                Text("1.4.1-plant-lookingforplan-foot")
//            }
//            Button(
//                onClick = nav01,
//                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
//            ) {
//                Icon(
//                    Icons.Filled.Favorite,
//                    contentDescription = "Localized description",
//                    modifier = Modifier.size(ButtonDefaults.IconSize)
//                )
//                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//                Text("1.2-plant-plan")
//            }
//            Button(
//                onClick = nav05,
//                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
//            ) {
//                Icon(
//                    Icons.Filled.Favorite,
//                    contentDescription = "Localized description",
//                    modifier = Modifier.size(ButtonDefaults.IconSize)
//                )
//                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//                Text("进入PlanList")
//            }
//        }

    }
}

