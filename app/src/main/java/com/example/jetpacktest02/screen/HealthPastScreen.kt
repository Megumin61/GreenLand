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
import android.graphics.Point
import android.util.Log
import android.widget.ProgressBar
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.composed
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.InspectorValueInfo
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.compose.MyTopAppBar
import com.example.jetpacktest02.screen.FriendList
import com.example.jetpacktest02.screen.IconButtonFriendList
import com.example.scaffolddemo.ui.theme.Gray1
import com.example.scaffolddemo.ui.theme.Green1
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

/**
 * The Bills screen.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true, widthDp = 393, heightDp = 851)
@Composable
fun HealthPastScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},

    ) {
    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Color.White, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )
    androidx.compose.material.Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "健康总结",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //设置字体粗细
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-25.dp, 0.dp)//向左偏移一段距离
                        )
                    }
                },
                //左侧按钮
                navigationIcon = {
                    IconButton(onClick = nav01) {
                        Icon(
                            painter = painterResource(id = R.drawable.g1_2_0_ic_arrow_left),
                            contentDescription = ""
                        )

                    }
                },
                //右侧按钮
                actions = {
//                    Image(
//                        painter = painterResource(id = R.drawable.g2_5_btn_friend),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .width(100.dp)
//                            .height(100.dp)
//                            .offset(-10.dp, 0.dp)
////                            .clickable(onClick = {userViewModel.uiState.value.pageState.value=3})
//                    )
                },

                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 0.dp, //设置阴影
            )
        }
    ){Column {

        PastHealthViewTabRow(nav02)

    }}




    /*Column{
        Text("1.2.1-plant-foot")
        Button(
            onClick = nav01,
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("1.1-Plant")
        }
    }*/


}


@Composable
fun LazyRowPlant01() {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.padding(0.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.g6_2_rowbg),
            contentDescription = null,
        )
        LazyRow(modifier = Modifier.width(320.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            content =
            {
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.g6_2_plant7),
                            contentDescription = null,
                        )
                        Text(text = "8.18", fontSize = 12.sp, color = Color(149, 152, 172))
                    }
                }
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.g6_2_plant8),
                            contentDescription = null,
                        )
                        Text(text = "8.25", fontSize = 12.sp, color = Color(149, 152, 172))
                    }
                }
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.g6_2_plant1),
                            contentDescription = null,
                        )
                        Text(text = "9.1", fontSize = 12.sp, color = Color(149, 152, 172))
                    }
                }
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.g6_2_plant2),
                            contentDescription = null,
                        )
                        Text(text = "9.8", fontSize = 12.sp, color = Color(149, 152, 172))
                    }
                }
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.g6_2_plant3),
                            contentDescription = null,
                        )
                        Text(text = "9.15", fontSize = 12.sp, color = Color(149, 152, 172))
                    }
                }
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.g6_2_plant4),
                            contentDescription = null,
                        )
                        Text(text = "9.22", fontSize = 12.sp, color = Color(149, 152, 172))
                    }
                }
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.g6_2_plant5),
                            contentDescription = null,
                        )
                        Text(text = "9.29", fontSize = 12.sp)
                    }
                }
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.g6_2_plant6),
                            contentDescription = null,
                        )
                        Text(text = "10.6", fontSize = 12.sp, color = Color(149, 152, 172))
                    }
                }


            }
        )

    }


}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PastHealthViewTabRow(nav01: () -> Unit={},
                         nav02: () -> Unit = {},) {
    //state为顶部的tab导航栏绑定参数
    var state by remember { mutableStateOf(0) }
    //pagerState为底部viewpager参数
    val pagerState: PagerState = remember { PagerState() }

    //将底部pager的参数和顶部导航栏的参数state绑定，让pager响应顶部导航栏参数变化
    LaunchedEffect(pagerState) {
        snapshotFlow { state }.collect { page ->
            pagerState.animateScrollToPage(page)
        }
    }
    //将底部pager的参数和顶部导航栏的参数state绑定
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            state = page
        }
    }
    val titles = listOf("本周", "过往")
    Column {
        androidx.compose.material.TabRow(
            backgroundColor = Color.Green,
            selectedTabIndex = state,
            indicator = @Composable { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.customTabIndicatorOffset(tabPositions[state]),
                    color = Color(26, 207, 163)
                )
            }
        ) {
            titles.forEachIndexed { index, title ->
                androidx.compose.material.Tab(
                    modifier = Modifier
                        .background(Color.White)
                        .width(10.dp),
                    selected = state == index,
                    onClick = { state = index },
                    text = {
                        androidx.compose.material.Text(
                            text = title,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W800,
                        )
                    },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Gray1
                )
            }
        }
    }

    HorizontalPager(count = 2, state = pagerState) { page ->
        Text(text = "Page: $page")
        //下面为要滑动切换的界面，可以通过判断page调用不同页面
        Box(
            modifier = Modifier
                .size(393.dp, 2000.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(246, 235, 222),
                            Color(195, 216, 190)
                        )
                    )
                )
        )
        {
            if (page == 0) {
                LazyColumn(

                    Modifier
                        .fillMaxWidth() // 宽度填满父空间
                        .height(851.dp)
                        .padding(top = 20.dp, start = 26.dp, end = 26.dp),


                    verticalArrangement = Arrangement.spacedBy(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    content = {
                        item{
                            Row (verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(24.dp)){
                                ImgPlant()
                                InformationRow()
                            }


                        }
                        item{
                            StepFrame()
                        }
                        item{
                            SitFrame()
                        }
                        item{
                            WaterFrame()
                        }
                        item{
                            EatFrame()
                        }

                        item{
                            ImgAdviceFrame()
                        }
                        item{
                            Button(onClick = nav02,
                                colors = ButtonDefaults.outlinedButtonColors(),
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier.padding(bottom = 10.dp)
                            ) {
                                ImgBtnGet()
                            }
                        }


                    })
            }
            if (page == 1) {
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    LazyRowPlant01()
                    LazyColumn(
                        Modifier
                            .fillMaxWidth() // 宽度填满父空间
                            .height(2000.dp)
                            .padding(top = 0.dp, start = 26.dp, end = 26.dp),


                        verticalArrangement = Arrangement.spacedBy(22.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,

                        content = {

                            item {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                                ) {
                                    ImgPlant()
                                    InformationRow()
                                }


                            }
                            item {
                                StepFrame()
                            }
                            item {
                                SitFrame()
                            }
                            item {
                                WaterFrame()
                            }
                            item {
                                ImgAdviceFrame()
                            }



                        })
                }
            }



        }
    }
}


