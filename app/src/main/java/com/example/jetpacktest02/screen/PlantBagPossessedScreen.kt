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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R

/**
 * 植物背包132123
 * 植物背包页面
 * 负责人：方凯荣
 * 对接人：
 */
@Preview(showBackground=true,widthDp=393,heightDp=851)
@Composable
fun PlantBagPossessedScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit={},

    ) {
    Image(
        painter = painterResource(id = R.drawable.g5_1_1_bg),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()

    )
    Column(modifier = Modifier.
    fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        PlantImage()
        Spacer(modifier = Modifier.height(0.dp))
        // TabRowDemo()
        BtnRow()
        Spacer(modifier = Modifier.height(15.dp))
        TabBAR()
        Spacer(modifier = Modifier.height(30.dp))
        BagList()
    }


}
//图片
@Composable
fun PlantImage(){
    Image(painter = painterResource(
        id = R.drawable.g5_1_1_img_flower),
        contentDescription = null,
        modifier = Modifier
            .width(119.dp)
            .padding(top = 70.dp))

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
            Text(text = "活力 ", fontSize = 16.sp, fontWeight = FontWeight.W700,color=Color(red = 44,green=110,blue=73))
            Text(text = "30", fontSize = 16.sp,fontWeight = FontWeight.W600,color=Color(red = 44,green=110,blue=73))
        }

    }
}
//栏---------------------------
@Composable
fun TabRowDemo() {
    var state = remember { mutableStateOf(0) }
    val titles = listOf("已拥有", "商城")

    Column {
        TabRow(selectedTabIndex = state.value,

            modifier = Modifier.padding(start=30.dp,end=30.dp),
            containerColor=Color.Transparent,



            ) {

            titles.forEachIndexed { index, title ->
                Tab(
                    modifier= Modifier
                        .height(20.dp)
                        .width(20.dp)
                        .padding(0.dp),
                    unselectedContentColor = Color.Gray,
                    selectedContentColor = Color.Black,

                    text = { Text(title,) },
                    selected = state.value == index,
                    onClick = { state.value = index }
                )
            }
        }



    }
}


@Composable
fun TabBar() {
    val names = listOf("已拥有", "商城")
    var selected by remember { mutableStateOf(0) }
    LazyRow(Modifier.padding(0.dp, 8.dp), contentPadding = PaddingValues(12.dp, 0.dp)) {
        itemsIndexed(names) { index, name ->
            Column(
                Modifier
                    .padding(12.dp, 4.dp)
                    .width(IntrinsicSize.Max)) {

                Text(name, fontSize = 15.sp,
                    color = if (index == selected) Color(0xfffa9e51) else Color(0xffb4b4b4)
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                        .height(2.dp)
                        .clip(RoundedCornerShape(1.dp))
                        .background(
                            if (index == selected) Color(0xfffa9e51) else Color.Transparent
                        )
                )
            }

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
        modifier=Modifier.fillMaxWidth().padding(start=30.dp, end = 30.dp),

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
            .height(340.dp),
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
fun TabBAR(){
    var state = remember { mutableStateOf(0) }
    val titles = listOf("已拥有", "商城")
    TabRow(
        selectedTabIndex = state.value,

        modifier = Modifier.padding(start=45.dp,end=45.dp),
        containerColor=Color.Transparent,indicator = @Composable { tabPositions ->

            val currentTabPosition = tabPositions[0]
            //修改指示器长度
            val currentTabWidth by animateDpAsState(
                targetValue = currentTabPosition.width /8,
                animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
            )
            //修改指示器偏移量为居中
            val indicatorOffset by animateDpAsState(
                targetValue = currentTabPosition.left + (currentTabPosition.width / 2 - currentTabWidth / 2),
                animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
            )
            //自带的Indicator指示器，只需改Modifier就可以了
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.BottomStart)
                    .offset(x = indicatorOffset)
                    .width(currentTabWidth)
                    .height(2.dp)//修改指示器高度为1dp，默认2dp
                // .padding(top=2.dp)
                , color = Color(14,181,171)
            )

        }) {
        titles.forEachIndexed { index, title ->
            Tab(
                modifier= Modifier
                    .height(30.dp)
                    .width(20.dp)
                    .padding(0.dp),
                unselectedContentColor = Color.Gray,
                selectedContentColor = Color.Black,

                text = { Text(title,) },
                selected = state.value == index,
                onClick = { state.value = index }
            )
        }

    }
}







