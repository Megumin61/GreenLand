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
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground=true,widthDp=393,heightDp=851)
@Composable
fun PlantBagPossessedScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit={},

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
                            text = "",
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
                painter = painterResource(id = R.drawable.g5_1_1_bg),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(1f),
                        contentScale = ContentScale.FillWidth




            )
            Column(modifier = Modifier.
            fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                PlantImage()

                Spacer(modifier = Modifier.height(8.dp))
                PlantBagViewTabRow()
            }


        }

    }




 

}
//图片
@Composable
fun PlantImage(){

    Box(contentAlignment = Alignment.BottomCenter){
        Image(painter = painterResource(
        id = R.drawable.g5_1_1_img_flower),
        contentDescription = null,
        modifier = Modifier
            .width(119.dp)
            .padding(bottom = 20.dp)
        )
        BtnRow()
    }


}

@Composable
fun ClothImage1(){
    Image(painter = painterResource(
        id = R.drawable.g5_1_1_cloth1),
        contentDescription = null,
        )

}
@Composable
fun ClothImage2(){
    Image(painter = painterResource(
        id = R.drawable.g5_1_1_cloth2),
        contentDescription = null,
    )

}
@Composable
fun ClothImage3(){
    Image(painter = painterResource(
        id = R.drawable.g5_1_1_cloth3),
        contentDescription = null,
    )

}
@Composable
fun ClothImage4(){
    Image(painter = painterResource(
        id = R.drawable.g5_1_1_cloth4),
        contentDescription = null,
    )

}
@Composable
fun ClothImage5(){
    Image(painter = painterResource(
        id = R.drawable.g5_1_1_cloth5),
        contentDescription = null,
    )

}
@Composable
fun ClothImage6(){
    Image(painter = painterResource(
        id = R.drawable.g5_1_1_cloth6),
        contentDescription = null,
    )

}

@Composable
fun BtnEnergyImg(){
    Image(painter = painterResource(
        id = R.drawable.g5_1_1_icbg_energyy),
        contentDescription = null,
    )

}
@Composable
fun BtnBgImg(){
    Image(painter = painterResource(
        id = R.drawable.g5_1_1_btn_toachieve),
        contentDescription = null,
    )

}

//按钮----------------------------
@Composable
fun BtnWear(){
    Button(onClick = { /*TODO*/ },
        Modifier
            .width(75.dp)
            .height(24.dp),
        colors = ButtonDefaults.outlinedButtonColors(),
        shape = RoundedCornerShape(//圆角
            50,50,50,50),


        contentPadding = PaddingValues(bottom = 0.dp,top=0.dp)
    ) {
        Image(painter = painterResource(
            id = R.drawable.g_5_1_1_btn_wear),
            contentDescription = null,
        )
    }
}
@Composable
fun Btnweared(){

    Button(onClick = { /*TODO*/ },
        colors = ButtonDefaults.outlinedButtonColors(),
        contentPadding = PaddingValues(0.dp)
    ) {
        Image(painter = painterResource(
            id = R.drawable.g_5_1_1_btn_weared),
            contentDescription = null,
        )
    }

}
@Composable
fun BtnToAchievement(){

    Button(onClick = { /*TODO*/ },
        colors = ButtonDefaults.outlinedButtonColors(),
        modifier = Modifier.padding(0.dp)
                ,contentPadding = PaddingValues(0.dp)
    ) {
        BtnBgImg()
    }

}
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
fun ClothGrid1(){
    Box(

        contentAlignment = Alignment.BottomCenter
    

    ) {
        ClothImage1()
        Column() {
            BtnWear()
            Spacer(modifier = Modifier.height(12.dp))
        }

    }
}
@Composable
fun ClothGrid2(){
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        ClothImage2()
        Column() {
            BtnWear()
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
@Composable
fun ClothGrid3(){
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        ClothImage3()
        Column() {
            BtnWear()
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
@Composable
fun ClothGrid4(){
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        ClothImage4()
        Column() {
            BtnWear()
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
@Composable
fun ClothGrid5(){
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        ClothImage5()
        Column() {
            BtnWear()
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
@Composable
fun ClothGrid6(){
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        ClothImage6()
        Column() {
            BtnWear()
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun BagRow1(){
    Row (
        horizontalArrangement = Arrangement.spacedBy(20.dp)
            ){
        ClothGrid1()
        ClothGrid2()
    }
}
@Composable
fun BagRow2(){
    Row (
        horizontalArrangement = Arrangement.spacedBy(20.dp)
         ){
        ClothGrid3()
        ClothGrid4()
    }
}
@Composable
fun BagRow3(){
    Row (
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ){
        ClothGrid5()
        ClothGrid6()
    }
}

@Composable
fun BtnRow(){
    Row (
        modifier= Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp),

        horizontalArrangement = Arrangement.SpaceBetween,


    ){
        BtnEnergyValue()
        BtnToAchievement()
    }
}

@Composable
fun BagList(){
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .height(380.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),

        content ={
        item{
            BagRow1()
        }
        item{
            BagRow2()
        }
        item{
            BagRow3()
        }
    })


}
@Composable
fun ShopList(){
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .height(380.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),

        content ={
            item{
                Row (
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ){
                    Box(//---------------装扮1
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Image(painter = painterResource(
                            id = R.drawable.g5_1_1_cloth01),
                            contentDescription = null,
                        )
                        Column() {
                            Button(onClick = { /*TODO*/ },
                                colors = ButtonDefaults.outlinedButtonColors(),
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier.offset(y=12.dp)
                            ) {
                                Image(painter = painterResource(
                                    id = R.drawable.g_5_1_1_btn_buy),
                                    contentDescription = null,
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                    Box(//---------------装扮1
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Image(painter = painterResource(
                            id = R.drawable.g5_1_1_cloth02),
                            contentDescription = null,
                        )
                        Column() {
                            Button(onClick = { /*TODO*/ },
                                colors = ButtonDefaults.outlinedButtonColors(),
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier.offset(y=12.dp)
                            ) {
                                Image(painter = painterResource(
                                    id = R.drawable.g_5_1_1_btn_buy),
                                    contentDescription = null,
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
            }
            item{
                Row (
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ){
                    Box(//---------------装扮1
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Image(painter = painterResource(
                            id = R.drawable.g5_1_1_cloth03),
                            contentDescription = null,
                        )
                        Column() {
                            Button(onClick = { /*TODO*/ },
                                colors = ButtonDefaults.outlinedButtonColors(),
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier.offset(y=12.dp)
                            ) {
                                Image(painter = painterResource(
                                    id = R.drawable.g_5_1_1_btn_buy),
                                    contentDescription = null,
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                    Box(//---------------装扮1
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Image(painter = painterResource(
                            id = R.drawable.g5_1_1_cloth04),
                            contentDescription = null,
                        )
                        Column() {
                            Button(onClick = { /*TODO*/ },
                                colors = ButtonDefaults.outlinedButtonColors(),
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier.offset(y=12.dp)
                            ) {
                                Image(painter = painterResource(
                                    id = R.drawable.g_5_1_1_btn_buy),
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



@OptIn(ExperimentalPagerApi::class)
@Composable
fun PlantBagViewTabRow(nav01: () -> Unit={},) {
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
    val titles = listOf("已拥有", "商城")
    Box(contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()){

        Column() {
            Column {
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
                    BagList()
                }
                if (page == 1) {
                    ShopList()
                }


            }
        }
    }


}






