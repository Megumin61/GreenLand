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
import android.text.style.BackgroundColorSpan
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.pullrefresh.pullRefreshIndicatorTransform
import androidx.compose.material3.*
import androidx.compose.material3.ListItemDefaults.containerColor
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.ui.Modifier

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.Gray1
import com.example.scaffolddemo.ui.theme.Green1
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * 植物背包132123
 * 植物背包页面
 * 负责人：方凯荣
 * 对接人：
 */
@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Preview(showBackground=true,widthDp=393,heightDp=851)
@Composable

fun PlantBagPossessedScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit={},
    nav02: () -> Unit={},
//    userViewModel: UserViewModel
) {
//state为顶部的tab导航栏绑定参数
    var state by remember { mutableStateOf(0) }
    //pagerState为底部viewpager参数
    val pagerState: PagerState = remember { PagerState() }

    var flowerPicSource:Int=R.drawable.g5_1_1_img_flower
    var btnPage1Source1:Int=R.drawable.g_5_1_1_btn_wear
    var btnPage1Source2:Int=R.drawable.g_5_1_1_btn_wear
    var btnPage1Source3:Int=R.drawable.g_5_1_1_btn_wear
    var btnPage1Source4:Int=R.drawable.g_5_1_1_btn_wear
    var btnPage1Source5:Int=R.drawable.g_5_1_1_btn_wear
    var btnPage1Source6:Int=R.drawable.g_5_1_1_btn_wear
    var btnPage1Source7:Int=R.drawable.g_5_1_1_btn_wear
    var btnPage1Source8:Int=R.drawable.g_5_1_1_btn_wear
    var btnPage2Source1:Int=R.drawable.g_5_1_1_btn_buy
    var btnPage2Source2:Int=R.drawable.g_5_1_1_btn_buy
    var btnPage2Source3:Int=R.drawable.g_5_1_1_btn_buy
    var btnPage2Source4:Int=R.drawable.g_5_1_1_btn_buy

    var ifbtn1pressed by remember{
        mutableStateOf(0)}

    if(ifbtn1pressed==0){
        flowerPicSource=R.drawable.g5_1_1_img_flower
        btnPage1Source1=R.drawable.g_5_1_1_btn_wear
    }
    if(ifbtn1pressed==1){
        flowerPicSource=R.drawable.g5_1_1_img_flower01
        btnPage1Source1=R.drawable.g_5_1_1_btn_weared
    }
    if(ifbtn1pressed==2){
        flowerPicSource=R.drawable.g5_1_1_img_flower02
        btnPage1Source2=R.drawable.g_5_1_1_btn_weared
    }
    if(ifbtn1pressed==3){
        flowerPicSource=R.drawable.g5_1_1_img_flower03
        btnPage1Source3=R.drawable.g_5_1_1_btn_weared
    }
    if(ifbtn1pressed==4){
        flowerPicSource=R.drawable.g5_1_1_img_flower04
        btnPage1Source4=R.drawable.g_5_1_1_btn_weared
    }
    if(ifbtn1pressed==5){
        flowerPicSource=R.drawable.g5_1_1_img_flower05
        btnPage1Source5=R.drawable.g_5_1_1_btn_weared
    }
    if(ifbtn1pressed==6){
        flowerPicSource=R.drawable.g5_1_1_img_flower06
        btnPage1Source6=R.drawable.g_5_1_1_btn_weared
    }
    if(ifbtn1pressed==7){
        flowerPicSource=R.drawable.g5_1_1_img_flower07
        btnPage1Source7=R.drawable.g_5_1_1_btn_weared
    }
    if(ifbtn1pressed==8){
        flowerPicSource=R.drawable.g5_1_1_img_flower10
        btnPage1Source8=R.drawable.g_5_1_1_btn_weared
    }





    var ifbtn2pressed1 by remember{
        mutableStateOf(0)}
    var ifbtn2pressed2 by remember{
        mutableStateOf(0)}
    var ifbtn2pressed3 by remember{
        mutableStateOf(0)}
    var ifbtn2pressed4 by remember{
        mutableStateOf(0)}
    if(ifbtn2pressed1==1){

        btnPage2Source1=R.drawable.g_5_1_1_btn_buyed
    }
    if(ifbtn2pressed2==2){

        btnPage2Source2=R.drawable.g_5_1_1_btn_buyed
    }
    if(ifbtn2pressed3==3){

        btnPage2Source3=R.drawable.g_5_1_1_btn_buyed
    }
    if(ifbtn2pressed4==4){

        btnPage2Source4=R.drawable.g_5_1_1_btn_buyed
    }



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
    val titles = listOf("已拥有", "商城")

    rememberSystemUiController().setStatusBarColor(
        Color(242,234,220), darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
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
                            text = "我的背包",
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

                backgroundColor = Color(242,234,220),
                contentColor = Color.Black,
                elevation = 0.dp, //设置阴影
            )
        }
    ){
        Box(contentAlignment = Alignment.TopCenter){

            Image(
                painter = painterResource(id = R.drawable.g5_1_1_bg),//背景图片
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(1f),
                        contentScale = ContentScale.FillWidth




            )
            Column(modifier = Modifier.
            fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Spacer(modifier = Modifier.height(0.dp))
//                Box(contentAlignment = Alignment.TopCenter){
//
//                    BtnRow()
//                }
//                Spacer(modifier = Modifier.height(10.dp))

                Box(contentAlignment = Alignment.TopCenter,
                    modifier = Modifier.fillMaxSize()){

                    Column() {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(contentAlignment = Alignment.TopCenter){

                                Image(painter = painterResource(
                                    id = flowerPicSource),//植物图片--------------------------------------------------------------------------------------------------
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(119.dp)
                                        .padding(top=25.dp)

                                )

                                Row (
                                    modifier= Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp),

                                    horizontalArrangement = Arrangement.SpaceBetween,


                                    ){
                                    BtnEnergyValue()
                                    Button(onClick = nav02,
                                        colors = ButtonDefaults.outlinedButtonColors(),
                                        modifier = Modifier.padding(0.dp)
                                        ,contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_1_1_btn_toachieve),
                                            contentDescription = null,
                                        )

                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            androidx.compose.material.TabRow(
                                modifier= Modifier
                                    .padding(horizontal = 27.dp)
                                    .height(40.dp),
                                backgroundColor = Color.Transparent,
                                selectedTabIndex = state,
                                indicator = @Composable { tabPositions ->
                                    androidx.compose.material.TabRowDefaults.Indicator(
                                        Modifier.customTabIndicatorOffset(tabPositions[state]),
                                        color = Color(26, 207, 163)
                                    )
                                }
                            ) {
                                titles.forEachIndexed { index, title ->
                                    androidx.compose.material.Tab(
                                        modifier = Modifier
                                            .background(Color.Transparent)
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
                        Spacer(modifier = Modifier.height(20.dp))
                        HorizontalPager(count = 2, state = pagerState) { page ->

                            //下面为要滑动切换的界面，可以通过判断page调用不同页面
                            if (page == 0) {
                                LazyColumn(//已拥有列表--------------------------------------------------------------------------------------------------
                                    Modifier
                                        .fillMaxWidth()
                                        .height(380.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(20.dp),

                                    content ={
                                        item{
                                            Row (//已拥有行1
                                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                                            ){
                                                Box(

                                                    contentAlignment = Alignment.BottomCenter


                                                ) {
                                                    Image(painter = painterResource(
                                                        id = R.drawable.g5_1_1_cloth1),
                                                        contentDescription = null,
                                                    )
                                                    Column() {
                                                        Button(onClick = { if (ifbtn1pressed==0||ifbtn1pressed==2||ifbtn1pressed==3||ifbtn1pressed==4||ifbtn1pressed==5||ifbtn1pressed==6||ifbtn1pressed==7||ifbtn1pressed==8){ifbtn1pressed=1} else{ifbtn1pressed=0} },//按钮:穿戴——————————————————————————————————————————————
                                                            Modifier
                                                                .width(75.dp)
                                                                .height(24.dp),
                                                            colors = ButtonDefaults.outlinedButtonColors(),
                                                            shape = RoundedCornerShape(//圆角
                                                                50,50,50,50),


                                                            contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                                        ) {
                                                            Image(painter = painterResource(
                                                                id =btnPage1Source1),
                                                                contentDescription = null,
                                                            )
                                                        }
                                                        Spacer(modifier = Modifier.height(12.dp))
                                                    }

                                                }
                                                Box(

                                                    contentAlignment = Alignment.BottomCenter


                                                ) {
                                                    Image(painter = painterResource(
                                                        id = R.drawable.g5_1_1_cloth2),
                                                        contentDescription = null,
                                                    )
                                                    Column() {
                                                        Button(onClick = { if (ifbtn1pressed==0||ifbtn1pressed==1||ifbtn1pressed==3||ifbtn1pressed==4||ifbtn1pressed==5||ifbtn1pressed==6||ifbtn1pressed==7||ifbtn1pressed==8){ifbtn1pressed=2} else{ifbtn1pressed=0} },
                                                            Modifier
                                                                .width(75.dp)
                                                                .height(24.dp),
                                                            colors = ButtonDefaults.outlinedButtonColors(),
                                                            shape = RoundedCornerShape(//圆角
                                                                50,50,50,50),


                                                            contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                                        ) {
                                                            Image(painter = painterResource(
                                                                id = btnPage1Source2),
                                                                contentDescription = null,
                                                            )
                                                        }
                                                        Spacer(modifier = Modifier.height(12.dp))
                                                    }

                                                }
                                            }
                                        }
                                        item{
                                            Row (//已拥有行2
                                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                                            ){
                                                Box(

                                                    contentAlignment = Alignment.BottomCenter


                                                ) {
                                                    Image(painter = painterResource(
                                                        id = R.drawable.g5_1_1_cloth3),
                                                        contentDescription = null,
                                                    )
                                                    Column() {
                                                        Button(onClick = { if (ifbtn1pressed==0||ifbtn1pressed==2||ifbtn1pressed==1||ifbtn1pressed==4||ifbtn1pressed==5||ifbtn1pressed==6||ifbtn1pressed==7||ifbtn1pressed==8){ifbtn1pressed=3} else{ifbtn1pressed=0}  },
                                                            Modifier
                                                                .width(75.dp)
                                                                .height(24.dp),
                                                            colors = ButtonDefaults.outlinedButtonColors(),
                                                            shape = RoundedCornerShape(//圆角
                                                                50,50,50,50),


                                                            contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                                        ) {
                                                            Image(painter = painterResource(
                                                                id = btnPage1Source3),
                                                                contentDescription = null,
                                                            )
                                                        }
                                                        Spacer(modifier = Modifier.height(12.dp))
                                                    }

                                                }
                                                Box(

                                                    contentAlignment = Alignment.BottomCenter


                                                ) {
                                                    Image(painter = painterResource(
                                                        id = R.drawable.g5_1_1_cloth4),
                                                        contentDescription = null,
                                                    )
                                                    Column() {
                                                        Button(onClick = { if (ifbtn1pressed==0||ifbtn1pressed==2||ifbtn1pressed==3||ifbtn1pressed==1||ifbtn1pressed==5||ifbtn1pressed==6||ifbtn1pressed==7||ifbtn1pressed==8){ifbtn1pressed=4} else{ifbtn1pressed=0} },
                                                            Modifier
                                                                .width(75.dp)
                                                                .height(24.dp),
                                                            colors = ButtonDefaults.outlinedButtonColors(),
                                                            shape = RoundedCornerShape(//圆角
                                                                50,50,50,50),


                                                            contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                                        ) {
                                                            Image(painter = painterResource(
                                                                id = btnPage1Source4),
                                                                contentDescription = null,
                                                            )
                                                        }
                                                        Spacer(modifier = Modifier.height(12.dp))
                                                    }

                                                }
                                            }
                                        }

                                        item{
                                            Row (//已拥有行3
                                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                                            ){
                                                Box(

                                                    contentAlignment = Alignment.BottomCenter


                                                ) {
                                                    Image(painter = painterResource(
                                                        id = R.drawable.g5_1_1_cloth5),
                                                        contentDescription = null,
                                                    )
                                                    Column() {
                                                        Button(onClick = {if (ifbtn1pressed==0||ifbtn1pressed==2||ifbtn1pressed==3||ifbtn1pressed==4||ifbtn1pressed==1||ifbtn1pressed==6){ifbtn1pressed=5} else{ifbtn1pressed=0} },
                                                            Modifier
                                                                .width(75.dp)
                                                                .height(24.dp),
                                                            colors = ButtonDefaults.outlinedButtonColors(),
                                                            shape = RoundedCornerShape(//圆角
                                                                50,50,50,50),


                                                            contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                                        ) {
                                                            Image(painter = painterResource(
                                                                id = btnPage1Source5),
                                                                contentDescription = null,
                                                            )
                                                        }
                                                        Spacer(modifier = Modifier.height(12.dp))
                                                    }

                                                }
                                                Box(

                                                    contentAlignment = Alignment.BottomCenter


                                                ) {
                                                    Image(painter = painterResource(
                                                        id = R.drawable.g5_1_1_cloth6),
                                                        contentDescription = null,
                                                    )
                                                    Column() {
                                                        Button(onClick = {if (ifbtn1pressed==0||ifbtn1pressed==2||ifbtn1pressed==3||ifbtn1pressed==4||ifbtn1pressed==5||ifbtn1pressed==1){ifbtn1pressed=6} else{ifbtn1pressed=0} },
                                                            Modifier
                                                                .width(75.dp)
                                                                .height(24.dp),
                                                            colors = ButtonDefaults.outlinedButtonColors(),
                                                            shape = RoundedCornerShape(//圆角
                                                                50,50,50,50),


                                                            contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                                        ) {
                                                            Image(painter = painterResource(
                                                                id = btnPage1Source6),
                                                                contentDescription = null,
                                                            )
                                                        }
                                                        Spacer(modifier = Modifier.height(12.dp))
                                                    }

                                                }
                                            }
                                        }
                                        if(ifbtn2pressed1==1||ifbtn2pressed2==2||ifbtn2pressed3==3||ifbtn2pressed4==4){
                                            item{
                                                Row (//已拥有行4
                                                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                                                ){
                                                    Box(

                                                        contentAlignment = Alignment.BottomCenter


                                                    ) {
                                                        Image(painter = painterResource(
                                                            id = R.drawable.g5_1_1_cloth01),
                                                            contentDescription = null,
                                                        )
                                                        Column() {5
                                                            Button(onClick = {if (ifbtn1pressed==0||ifbtn1pressed==2||ifbtn1pressed==3||ifbtn1pressed==4||ifbtn1pressed==1||ifbtn1pressed==6||ifbtn1pressed==5||ifbtn1pressed==8){ifbtn1pressed=7} else{ifbtn1pressed=0} },
                                                                Modifier
                                                                    .width(75.dp)
                                                                    .height(24.dp),
                                                                colors = ButtonDefaults.outlinedButtonColors(),
                                                                shape = RoundedCornerShape(//圆角
                                                                    50,50,50,50),


                                                                contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                                            ) {
                                                                Image(painter = painterResource(
                                                                    id = btnPage1Source7),
                                                                    contentDescription = null,
                                                                )
                                                            }
                                                            Spacer(modifier = Modifier.height(12.dp))
                                                        }

                                                    }
                                                    Box(

                                                        contentAlignment = Alignment.BottomCenter


                                                    ) {
                                                        Image(painter = painterResource(
                                                            id = R.drawable.g5_1_1_cloth04),
                                                            contentDescription = null,
                                                        )
                                                        Column() {
                                                            Button(onClick = {if (ifbtn1pressed==0||ifbtn1pressed==2||ifbtn1pressed==3||ifbtn1pressed==4||ifbtn1pressed==5||ifbtn1pressed==1||ifbtn1pressed==6||ifbtn1pressed==7){ifbtn1pressed=8} else{ifbtn1pressed=0} },
                                                                Modifier
                                                                    .width(75.dp)
                                                                    .height(24.dp),
                                                                colors = ButtonDefaults.outlinedButtonColors(),
                                                                shape = RoundedCornerShape(//圆角
                                                                    50,50,50,50),


                                                                contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                                            ) {
                                                                Image(painter = painterResource(
                                                                    id = btnPage1Source8),
                                                                    contentDescription = null,
                                                                )
                                                            }
                                                            Spacer(modifier = Modifier.height(12.dp))
                                                        }

                                                    }
                                                }
                                            }
                                        }

                                    })
                            }
                            if (page == 1) {
                                LazyColumn(//商城列表--------------------------------------------------------------------------------------------------
                                    Modifier
                                        .fillMaxWidth()
                                        .height(380.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(20.dp),

                                    content ={
                                        item{
                                            Row (//商城行1
                                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                                            ){
                                                Box(

                                                    contentAlignment = Alignment.BottomCenter


                                                ) {
                                                    Image(painter = painterResource(
                                                        id = R.drawable.g5_1_1_cloth01),
                                                        contentDescription = null,
                                                    )
                                                    Column() {
                                                        Button(onClick = { ifbtn2pressed1=1 },
                                                            Modifier
                                                                .width(75.dp)
                                                                .height(24.dp),
                                                            colors = ButtonDefaults.outlinedButtonColors(),
                                                            shape = RoundedCornerShape(//圆角
                                                                50,50,50,50),


                                                            contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                                        ) {
                                                            Image(painter = painterResource(
                                                                id =btnPage2Source1),
                                                                contentDescription = null,
                                                            )
                                                        }
                                                        Spacer(modifier = Modifier.height(12.dp))
                                                    }

                                                }
                                                Box(

                                                    contentAlignment = Alignment.BottomCenter


                                                ) {
                                                    Image(painter = painterResource(
                                                        id = R.drawable.g5_1_1_cloth02),
                                                        contentDescription = null,
                                                    )
                                                    Column() {
                                                        Button(onClick = { ifbtn2pressed2=2 },
                                                            Modifier
                                                                .width(75.dp)
                                                                .height(24.dp),
                                                            colors = ButtonDefaults.outlinedButtonColors(),
                                                            shape = RoundedCornerShape(//圆角
                                                                50,50,50,50),


                                                            contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                                        ) {
                                                            Image(painter = painterResource(
                                                                id = btnPage2Source2),
                                                                contentDescription = null,
                                                            )
                                                        }
                                                        Spacer(modifier = Modifier.height(12.dp))
                                                    }

                                                }
                                            }
                                        }
                                        item{
                                            Row (//商城行1
                                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                                            ){
                                                Box(

                                                    contentAlignment = Alignment.BottomCenter


                                                ) {
                                                    Image(painter = painterResource(
                                                        id = R.drawable.g5_1_1_cloth03),
                                                        contentDescription = null,
                                                    )
                                                    Column() {
                                                        Button(onClick = { ifbtn2pressed3=3 },
                                                            Modifier
                                                                .width(75.dp)
                                                                .height(24.dp),
                                                            colors = ButtonDefaults.outlinedButtonColors(),
                                                            shape = RoundedCornerShape(//圆角
                                                                50,50,50,50),


                                                            contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                                        ) {
                                                            Image(painter = painterResource(
                                                                id = btnPage2Source3),
                                                                contentDescription = null,
                                                            )
                                                        }
                                                        Spacer(modifier = Modifier.height(12.dp))
                                                    }

                                                }
                                                Box(

                                                    contentAlignment = Alignment.BottomCenter


                                                ) {
                                                    Image(painter = painterResource(
                                                        id = R.drawable.g5_1_1_cloth04),
                                                        contentDescription = null,
                                                    )
                                                    Column() {
                                                        Button(onClick = { ifbtn2pressed4=4 },
                                                            Modifier
                                                                .width(75.dp)
                                                                .height(24.dp),
                                                            colors = ButtonDefaults.outlinedButtonColors(),
                                                            shape = RoundedCornerShape(//圆角
                                                                50,50,50,50),


                                                            contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                                        ) {
                                                            Image(painter = painterResource(
                                                                id =btnPage2Source4),
                                                                contentDescription = null,
                                                            )
                                                        }
                                                        Spacer(modifier = Modifier.height(12.dp))
                                                    }

                                                }
                                            }
                                        }

                                    })
                            }


                        }
                    }
                }

            }


        }

    }




 

}
//图片



@Composable
fun BtnEnergyImg(){
    Image(painter = painterResource(
        id = R.drawable.g5_1_1_icbg_energyy),
        contentDescription = null,
    )

}

//按钮----------------------------


@Composable
fun BtnEnergyValue(){

   Box(contentAlignment = Alignment.Center, modifier = Modifier.offset(y=7.dp)){
       BtnEnergyImg()
         Row(modifier = Modifier.padding(start=25.dp)) {
         Text(text = "能量 ", fontSize = 16.sp, fontWeight = FontWeight.W700,color=Color(red = 44,green=110,blue=73))
         Text(text = "30", fontSize = 16.sp,fontWeight = FontWeight.W600,color=Color(red = 44,green=110,blue=73))
     }

   }
}






@Composable
fun BtnRow(nav02: () -> Unit={},){
    Row (
        modifier= Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),

        horizontalArrangement = Arrangement.SpaceBetween,


    ){
        BtnEnergyValue()
        Button(onClick = nav02,
            colors = ButtonDefaults.outlinedButtonColors(),
            modifier = Modifier.padding(0.dp)
            ,contentPadding = PaddingValues(0.dp)
        ) {
            Image(painter = painterResource(
                id = R.drawable.g5_1_1_btn_toachieve),
                contentDescription = null,
            )

        }
    }
}





@OptIn(ExperimentalPagerApi::class)
@Composable
fun PlantBagViewTabRow(nav02: () -> Unit={},) {



}






