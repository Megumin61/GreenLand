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
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
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
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Preview(showBackground=true,widthDp=393,heightDp=851)
@Composable

fun PlantBagAchievementScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit={},
    nav02: () -> Unit={},
//    userViewModel: UserViewModel
) {

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
                painter = painterResource(id = R.drawable.g5_2_1bg),//背景图片
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

                PlantBagAchievementViewTabRow()
            }


        }

    }




 

}
//图片



//按钮----------------------------

@Composable
fun BtnToCloth(
               nav02: () -> Unit={},){

    Button(onClick = nav02 ,
        colors = ButtonDefaults.outlinedButtonColors(),
        modifier = Modifier.padding(0.dp)
                ,contentPadding = PaddingValues(0.dp)
    ) {
        Image(painter = painterResource(
            id = R.drawable.g5_1_2_btn_tocloth),
            contentDescription = null,
        )
    }

}







@Composable
fun BtnRowPage2(
                nav02: () -> Unit={},){
    Row (
        modifier= Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),

        horizontalArrangement = Arrangement.SpaceBetween,


    ){
        BtnEnergyValue()
        BtnToCloth(nav02)
    }
}





@OptIn(ExperimentalPagerApi::class)
@Composable
fun PlantBagAchievementViewTabRow(nav01: () -> Unit={},
                                  nav02: () -> Unit={},) {
    //state为顶部的tab导航栏绑定参数
    var state by remember { mutableStateOf(0) }
    //pagerState为底部viewpager参数
    val pagerState: PagerState = remember { PagerState() }

    var achievePicSource:Int=R.drawable.g5_1_1_img_flower
    var btnSource1:Int=R.drawable.g5_2_1_btn_wear
    var btnSource2:Int=R.drawable.g5_2_1_btn_wear
    var btnSource3:Int=R.drawable.g5_2_1_btn_wear
    var btnSource4:Int=R.drawable.g5_2_1_btn_wear
    var btnSource5:Int=R.drawable.g5_2_1_btn_wear
    var btnSource6:Int=R.drawable.g5_2_1_btn_wear
    var btnSource7:Int=R.drawable.g5_2_1_btn_weared
    var btnSource8:Int=R.drawable.g5_2_1_btn_weared
    var btnSource9:Int=R.drawable.g5_2_1_btn_wear
    var btnSource10:Int=R.drawable.g5_2_1_btn_wear


    var ifbtnpressed by remember{
        mutableStateOf(0)}

    if(ifbtnpressed==0){
        achievePicSource=R.drawable.g5_2_1_img_achieve00
        btnSource1=R.drawable.g5_2_1_btn_wear
    }
    if(ifbtnpressed==1){
        achievePicSource=R.drawable.g5_2_1_img_achieve01
        btnSource1=R.drawable.g5_2_1_btn_weared
    }
    if(ifbtnpressed==2){
        achievePicSource=R.drawable.g5_2_1_img_achieve02
        btnSource2=R.drawable.g5_2_1_btn_weared
    }
    if(ifbtnpressed==3){
        achievePicSource=R.drawable.g5_2_1_img_achieve03
        btnSource3=R.drawable.g5_2_1_btn_weared
    }
    if(ifbtnpressed==4){
        achievePicSource=R.drawable.g5_2_1_img_achieve04
        btnSource4=R.drawable.g5_2_1_btn_weared
    }
    if(ifbtnpressed==5){
        achievePicSource=R.drawable.g5_2_1_img_achieve05
        btnSource5=R.drawable.g5_2_1_btn_weared
    }
    if(ifbtnpressed==6){
        achievePicSource=R.drawable.g5_2_1_img_achieve06
        btnSource6=R.drawable.g5_2_1_btn_weared
    }
//    if(ifbtnpressed==7){
//        achievePicSource=R.drawable.g5_2_1_img_achieve07
//        btnSource7=R.drawable.g5_2_1_btn_weared
//    }
//    if(ifbtnpressed==8){
//        achievePicSource=R.drawable.g5_2_1_img_achieve08
//        btnSource8=R.drawable.g5_2_1_btn_weared
//    }
    if(ifbtnpressed==9){
        achievePicSource=R.drawable.g5_2_1_img_achieve09
        btnSource9=R.drawable.g5_2_1_btn_weared
    }
    if(ifbtnpressed==10){
        achievePicSource=R.drawable.g5_2_1_img_achieve100
        btnSource10=R.drawable.g5_2_1_btn_weared
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
    val titles = listOf("活力成就", "社交成就","特殊成就")
    Box(contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()){

        Column() {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(contentAlignment = Alignment.TopCenter){
                    Image(painter = painterResource(
                        id = achievePicSource),//植物图片--------------------------------------------------------------------------------------------------
                        contentDescription = null,
                        modifier = Modifier
//                            .width(119.dp)
                            .padding(top = 0.dp)

                    )
                    BtnRowPage2(nav02)
                }

                Spacer(modifier = Modifier.height(34.dp))
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
            HorizontalPager(count = 3, state = pagerState) { page ->

                //下面为要滑动切换的界面，可以通过判断page调用不同页面
                if (page == 0) {
                    LazyColumn(//活力成就列表--------------------------------------------------------------------------------------------------
                        Modifier
                            .fillMaxWidth()
                            .height(380.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(15.dp),

                        content ={
                            item{
                                Row (//活力成就行1
                                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                                ){
                                    Box(

                                        contentAlignment = Alignment.BottomCenter


                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_2_1_achieve1),
                                            contentDescription = null,
                                        )
                                        Column() {
                                            Button(onClick = {
                                            if (ifbtnpressed==0||
                                                ifbtnpressed==2||
                                                ifbtnpressed==3||
                                                ifbtnpressed==4||
                                                ifbtnpressed==5||
                                                ifbtnpressed==6||
                                                ifbtnpressed==7||
                                                ifbtnpressed==8||
                                                ifbtnpressed==9||
                                                ifbtnpressed==10)
                                                     {ifbtnpressed=1}
                                            else{ifbtnpressed=0}
                                                             },//按钮:佩戴——————————————————————————————————————————————
                                                Modifier
                                                    .width(75.dp)
                                                    .height(24.dp),
                                                colors = ButtonDefaults.outlinedButtonColors(),
                                                shape = RoundedCornerShape(//圆角
                                                    50,50,50,50),


                                                contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                            ) {
                                                Image(painter = painterResource(
                                                    id =btnSource1),
                                                    contentDescription = null,
                                                )
                                            }

                                        }

                                    }
                                    Box(

                                        contentAlignment = Alignment.BottomCenter


                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_2_1_achieve2),
                                            contentDescription = null,
                                        )
                                        Column() {
                                            Button(onClick = {
                                                if (ifbtnpressed==0||
                                                    ifbtnpressed==1||
                                                    ifbtnpressed==3||
                                                    ifbtnpressed==4||
                                                    ifbtnpressed==5||
                                                    ifbtnpressed==6||
                                                    ifbtnpressed==7||
                                                    ifbtnpressed==8||
                                                    ifbtnpressed==9||
                                                    ifbtnpressed==10)
                                                {ifbtnpressed=2}
                                                else{ifbtnpressed=0}
                                            },//按钮:佩戴——————————————————————————————————————————————
                                                Modifier
                                                    .width(75.dp)
                                                    .height(24.dp),
                                                colors = ButtonDefaults.outlinedButtonColors(),
                                                shape = RoundedCornerShape(//圆角
                                                    50,50,50,50),


                                                contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                            ) {
                                                Image(painter = painterResource(
                                                    id =btnSource2),
                                                    contentDescription = null,
                                                )
                                            }

                                        }

                                    }
                                    Box(

                                        contentAlignment = Alignment.BottomCenter


                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_2_1_achieve3),
                                            contentDescription = null,
                                        )
                                        Column() {
                                            Button(onClick = {
                                                if (ifbtnpressed==0||
                                                    ifbtnpressed==2||
                                                    ifbtnpressed==1||
                                                    ifbtnpressed==4||
                                                    ifbtnpressed==5||
                                                    ifbtnpressed==6||
                                                    ifbtnpressed==7||
                                                    ifbtnpressed==8||
                                                    ifbtnpressed==9||
                                                    ifbtnpressed==10)
                                                {ifbtnpressed=3}
                                                else{ifbtnpressed=0}
                                            },//按钮:佩戴——————————————————————————————————————————————
                                                Modifier
                                                    .width(75.dp)
                                                    .height(24.dp),
                                                colors = ButtonDefaults.outlinedButtonColors(),
                                                shape = RoundedCornerShape(//圆角
                                                    50,50,50,50),


                                                contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                            ) {
                                                Image(painter = painterResource(
                                                    id =btnSource3),
                                                    contentDescription = null,
                                                )
                                            }

                                        }

                                    }
                                }
                            }
                            item{
                                Row (//活力成就行2
                                    horizontalArrangement = Arrangement.spacedBy(45.dp)
                                ){
                                    Box(

                                        contentAlignment = Alignment.BottomCenter


                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_2_1_achieve4),
                                            contentDescription = null,
                                        )
                                        Column() {
                                            Button(onClick = {
                                                if (ifbtnpressed==0||
                                                    ifbtnpressed==2||
                                                    ifbtnpressed==3||
                                                    ifbtnpressed==1||
                                                    ifbtnpressed==5||
                                                    ifbtnpressed==6||
                                                    ifbtnpressed==7||
                                                    ifbtnpressed==8||
                                                    ifbtnpressed==9||
                                                    ifbtnpressed==10)
                                                {ifbtnpressed=4}
                                                else{ifbtnpressed=0}
                                            },//按钮:佩戴——————————————————————————————————————————————
                                                Modifier
                                                    .width(75.dp)
                                                    .height(24.dp),
                                                colors = ButtonDefaults.outlinedButtonColors(),
                                                shape = RoundedCornerShape(//圆角
                                                    50,50,50,50),


                                                contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                            ) {
                                                Image(painter = painterResource(
                                                    btnSource4),
                                                    contentDescription = null,
                                                )
                                            }

                                        }

                                    }
                                    Box(

                                        contentAlignment = Alignment.BottomCenter,
                                        modifier = Modifier
                                            .width(75.dp)
                                            .height(24.dp)

                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_2_1_achieve_empty),
                                            contentDescription = null,
                                        )


                                    }
                                    Box(

                                        contentAlignment = Alignment.BottomCenter,
                                        modifier = Modifier
                                            .width(75.dp)
                                            .height(24.dp)


                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_2_1_achieve_empty),
                                            contentDescription = null,
                                        )


                                    }
                                }
                            }



                        })
                }
                if (page == 1) {
                    LazyColumn(//社交成就列表--------------------------------------------------------------------------------------------------
                        Modifier
                            .fillMaxWidth()
                            .height(380.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(15.dp),

                        content ={
                            item{
                                Row (//社交成就行1
                                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                                ){
                                    Box(

                                        contentAlignment = Alignment.BottomCenter


                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_2_1_achieve5),
                                            contentDescription = null,
                                        )
                                        Column() {
                                            Button(onClick = {
                                                if (ifbtnpressed==0||
                                                    ifbtnpressed==2||
                                                    ifbtnpressed==1||
                                                    ifbtnpressed==4||
                                                    ifbtnpressed==3||
                                                    ifbtnpressed==6||
                                                    ifbtnpressed==7||
                                                    ifbtnpressed==8||
                                                    ifbtnpressed==9||
                                                    ifbtnpressed==10)
                                                {ifbtnpressed=5}
                                                else{ifbtnpressed=0}
                                            },//按钮:佩戴——————————————————————————————————————————————
                                                Modifier
                                                    .width(75.dp)
                                                    .height(24.dp),
                                                colors = ButtonDefaults.outlinedButtonColors(),
                                                shape = RoundedCornerShape(//圆角
                                                    50,50,50,50),


                                                contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                            ) {
                                                Image(painter = painterResource(
                                                    id =btnSource5),
                                                    contentDescription = null,
                                                )
                                            }

                                        }

                                    }
                                    Box(

                                        contentAlignment = Alignment.BottomCenter


                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_2_1_achieve6),
                                            contentDescription = null,
                                        )
                                        Column() {
                                            Button(onClick = {
                                                if (ifbtnpressed==0||
                                                    ifbtnpressed==2||
                                                    ifbtnpressed==1||
                                                    ifbtnpressed==4||
                                                    ifbtnpressed==5||
                                                    ifbtnpressed==3||
                                                    ifbtnpressed==7||
                                                    ifbtnpressed==8||
                                                    ifbtnpressed==9||
                                                    ifbtnpressed==10)
                                                {ifbtnpressed=6}
                                                else{ifbtnpressed=0}
                                            },//按钮:佩戴——————————————————————————————————————————————
                                                Modifier
                                                    .width(75.dp)
                                                    .height(24.dp),
                                                colors = ButtonDefaults.outlinedButtonColors(),
                                                shape = RoundedCornerShape(//圆角
                                                    50,50,50,50),


                                                contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                            ) {
                                                Image(painter = painterResource(
                                                    id =btnSource6),
                                                    contentDescription = null,
                                                )
                                            }

                                        }

                                    }
                                    Box(

                                        contentAlignment = Alignment.BottomCenter


                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_2_1_achieve7),
                                            contentDescription = null,
                                        )
                                        Column() {
                                            Button(onClick = { },
                                                enabled=false,//按钮:佩戴——————————————————————————————————————————————
                                                modifier=Modifier
                                                    .width(75.dp)
                                                    .height(24.dp),
                                                colors = ButtonDefaults.outlinedButtonColors(),
                                                interactionSource = MutableInteractionSource(),
                                                shape = RoundedCornerShape(//圆角
                                                    50,50,50,50),


                                                contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                            ) {
                                                Image(painter = painterResource(
                                                    id =R.drawable.g5_2_1_btn_unattained),
                                                    contentDescription = null,
                                                )
                                            }

                                        }

                                    }
                                }
                            }
                            item{
                                Row (//社交成就行2
                                    horizontalArrangement = Arrangement.spacedBy(40.dp)
                                ){
                                    Box(

                                        contentAlignment = Alignment.BottomCenter


                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_2_1_achieve8),
                                            contentDescription = null,
                                        )
                                        Column() {
                                            Button(onClick = { },
                                                enabled=false,//按钮:佩戴——————————————————————————————————————————————
                                                modifier= Modifier
                                                    .width(75.dp)
                                                    .height(24.dp),
                                                colors = ButtonDefaults.outlinedButtonColors(),
                                                shape = RoundedCornerShape(//圆角
                                                    50,50,50,50),


                                                contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                            ) {
                                                Image(painter = painterResource(
                                                    id =R.drawable.g5_2_1_btn_unattained),
                                                    contentDescription = null,
                                                )
                                            }

                                        }

                                    }
                                    Box(

                                        contentAlignment = Alignment.BottomCenter,
                                        modifier = Modifier
                                            .width(75.dp)
                                            .height(24.dp)


                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_2_1_achieve_empty),
                                            contentDescription = null,
                                        )


                                    }
                                    Box(

                                        contentAlignment = Alignment.BottomCenter,
                                        modifier = Modifier
                                            .width(75.dp)
                                            .height(24.dp)


                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_2_1_achieve_empty),
                                            contentDescription = null,
                                        )


                                    }
                                }
                            }



                        })
                }
                if (page == 2) {
                    LazyColumn(//特殊成就列表--------------------------------------------------------------------------------------------------
                        Modifier
                            .fillMaxWidth()
                            .height(380.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(15.dp),

                        content ={
                            item{
                                Row (//社交成就行1
                                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                                ){
                                    Box(

                                        contentAlignment = Alignment.BottomCenter


                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_2_1_achieve9),
                                            contentDescription = null,
                                        )
                                        Column() {
                                            Button(onClick = {
                                                if (ifbtnpressed==0||
                                                    ifbtnpressed==2||
                                                    ifbtnpressed==1||
                                                    ifbtnpressed==4||
                                                    ifbtnpressed==5||
                                                    ifbtnpressed==6||
                                                    ifbtnpressed==7||
                                                    ifbtnpressed==8||
                                                    ifbtnpressed==3||
                                                    ifbtnpressed==10)
                                                {ifbtnpressed=9}
                                                else{ifbtnpressed=0}
                                            },//按钮:佩戴——————————————————————————————————————————————
                                                Modifier
                                                    .width(75.dp)
                                                    .height(24.dp),
                                                colors = ButtonDefaults.outlinedButtonColors(),
                                                shape = RoundedCornerShape(//圆角
                                                    50,50,50,50),


                                                contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                            ) {
                                                Image(painter = painterResource(
                                                    id =btnSource9),
                                                    contentDescription = null,
                                                )
                                            }

                                        }

                                    }
                                    Box(

                                        contentAlignment = Alignment.BottomCenter


                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_2_1_achieve10),
                                            contentDescription = null,
                                        )
                                        Column() {
                                            Button( onClick = {
                                                if (ifbtnpressed==0||
                                                    ifbtnpressed==2||
                                                    ifbtnpressed==1||
                                                    ifbtnpressed==4||
                                                    ifbtnpressed==5||
                                                    ifbtnpressed==6||
                                                    ifbtnpressed==7||
                                                    ifbtnpressed==8||
                                                    ifbtnpressed==9||
                                                    ifbtnpressed==3)
                                                {ifbtnpressed=10}
                                                else{ifbtnpressed=0}
                                             },//按钮:佩戴——————————————————————————————————————————————
                                                Modifier
                                                    .width(75.dp)
                                                    .height(24.dp),
                                                colors = ButtonDefaults.outlinedButtonColors(),
                                                shape = RoundedCornerShape(//圆角
                                                    50,50,50,50),


                                                contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
                                            ) {
                                                Image(painter = painterResource(
                                                    id =btnSource10),
                                                    contentDescription = null,
                                                )
                                            }

                                        }

                                    }
                                    Box(

                                        contentAlignment = Alignment.BottomCenter,
                                        modifier = Modifier
                                            .width(75.dp)
                                            .height(24.dp)


                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g5_2_1_achieve_empty),
                                            contentDescription = null,
                                        )


                                    }

                                }
                            }




                        })
                }


            }
        }
    }


}






