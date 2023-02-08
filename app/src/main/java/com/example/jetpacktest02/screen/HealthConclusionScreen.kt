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
import com.example.jetpacktest02.compose.MyTopAppBar
import com.example.scaffolddemo.ui.theme.Gray1
import com.example.scaffolddemo.ui.theme.Green1
import com.google.accompanist.pager.*
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

/**
 * The Bills screen.
 */
@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground=true,widthDp=393,heightDp=851)
@Composable
fun HealthConclusionScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit={},
    nav02: () -> Unit={}

) {
    Column {





        HealthTopAppBar()
        HealthViewTabRow()
        Box(
            modifier = Modifier
                .size(393.dp, 851.dp)
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
            /*Column(modifier = Modifier
                .padding(top = 20.dp, start = 26.dp, end = 26.dp)
                , verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                StepFrame()
                SitFrame()
                WaterFrame()

            }*/


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
                        Button(onClick = nav01,
                            colors = ButtonDefaults.outlinedButtonColors(),
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.padding(bottom = 10.dp)
                        ) {
                            ImgBtnGet()
                        }
                    }


                })
        }
    }




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

private fun identifyClickItem(points: List<Point>, x: Float, y: Float): Int {
    for ((index, point) in points.withIndex()) {
        if (x > point.x+20 && x < point.x + 20+40) {
            return index
        }
    }
    return -1
}
//条形统计图--------------------------------------------------------------------------
//----------------------------------------------------------------------------------
@Composable
fun BarChart() {
    val point = listOf(
        Point(10, 200), Point(115, 100), Point(218, 30),
        Point(323, 200), Point(426, 120), Point(532, 10),
        Point(635, 100)
    )
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .background(Color.White)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        val i = identifyClickItem(point, it.x, it.y)
                        Log.d("pointerInput", "onTap: ${it.x} ${it.y} item:$i")

                    }
                )
            }
    ) {
        //绘制 X轴 Y轴
        /*drawLine(
            start = Offset(10f, 600f),
            end = Offset(10f, 0f),
            color = Color.Black,
            strokeWidth = 2f
        )
        drawLine(
            start = Offset(10f, 600f),
            end = Offset(850f, 600f),
            color = Color.Black,
            strokeWidth = 2f
        )*/
        start = true
        for (p in point) {
            /*drawRect(
                color = Color(207,235,223),
                topLeft = Offset((p.x + 20).toFloat(), 600 - (600 - p.y) * heightPre),
                size = Size(60f, (600 - p.y) * heightPre)
            )*/
            if (p.y<150)
            { drawRoundRect(
                color = Color(207,235,223),
                topLeft = Offset((p.x +125).toFloat(), 300 - (300 - p.y) * heightPre),
                size = Size(40f, (300 - p.y) * heightPre)
                , cornerRadius = CornerRadius(30f,30f)
            )}
            else{
                drawRoundRect(
                    color = Color(255,226,194),
                    topLeft = Offset((p.x +125).toFloat(), 300 - (300 - p.y) * heightPre),
                    size = Size(40f, (300 - p.y) * heightPre)
                    , cornerRadius = CornerRadius(30f,30f)
                )
            }


            /* drawCircle(
                 color = Color(198,199,209),
                 radius = 15f,
                 center=Offset((p.x +100).toFloat(), 435f)
             )*/



        }


    }
    /*Row(modifier= Modifier.padding(start=26.dp,end=10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "周一",color=Color(198,199,209))
        Text(text = "周二",color=Color(198,199,209))
        Text(text = "周三",color=Color(198,199,209))
        Text(text = "周四",color=Color(198,199,209))
        Text(text = "周五",color=Color(198,199,209))
        Text(text = "周六",color=Color(198,199,209))
        Text(text = "周日",color=Color(198,199,209))

    }*/

}



//步数卡片---------------------------------------------------
@Composable
fun StepFrame()
{
    Box (contentAlignment = Alignment.TopCenter){
        ImgStepFrame()
        Column(modifier = Modifier.padding(top=60.dp)) {
            Row (horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp)){
                AverageStepInf()
                CompareStepInf()
            }
            BarChart()
            Spacer(modifier =Modifier.height(58.dp))
        }

    }
}

@Composable
fun ImgStepFrame(){
    Image(
        painter = painterResource(id = R.drawable.g6_1_card_foot),
        contentDescription = null,
    )

}
@Composable
fun AverageStepInf()
{
    Column() {
        Row (horizontalArrangement = Arrangement.spacedBy(7.dp), verticalAlignment = Alignment.Bottom){
            Text(text = "8023 ",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color= Color.DarkGray)
            Text(text ="34%",
                color= Color(red = 26,green=207,blue=163)
            )
            iconIncrease()
        }
        Text(text = "平均步数",
            color= Color(red =198,green=199,blue=209)
        )
    }

}
@Composable
fun CompareStepInf()
{
    Column() {
        Row (horizontalArrangement = Arrangement.spacedBy(7.dp), verticalAlignment = Alignment.Bottom){
            Text(text = "1765 ",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color= Color.DarkGray)
            Text(text ="20%",
                color= Color(red = 255,green=1047,blue=104)
            )
            iconDecrease()
        }
        Text(text = "与健康步数相比",
            color= Color(red =198,green=199,blue=209)
        )
    }

}

//久坐时间卡片---------------------------------------------------
@Composable
fun SitFrame()
{
    Box (contentAlignment = Alignment.TopCenter){
        ImgSitFrame()
        Column(modifier = Modifier.padding(top=60.dp)) {
            Row (horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp)){
                AverageSitInf()
                CompareSitInf()
            }
            Spacer(modifier = Modifier.height(10.dp))
            BarChart()
            Spacer(modifier =Modifier.height(58.dp))
        }

    }
}

@Composable
fun ImgSitFrame(){
    Image(
        painter = painterResource(id = R.drawable.g6_1_card_sittime),
        contentDescription = null,
    )

}
@Composable
fun AverageSitInf()
{
    Column() {
        Row (horizontalArrangement = Arrangement.spacedBy(7.dp), verticalAlignment = Alignment.Bottom){
            Text(text = "6:31:20 ",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                color= Color.DarkGray)
            Text(text ="34%",
                color= Color(red = 26,green=207,blue=163)
            )
            iconIncrease()
        }
        Text(text = "平均久坐时间",
            color= Color(red =198,green=199,blue=209),
            fontSize = 12.sp
        )
    }

}
@Composable
fun CompareSitInf()
{
    Column() {
        Row (horizontalArrangement = Arrangement.spacedBy(7.dp), verticalAlignment = Alignment.Bottom){
            Text(text = "58:20 ",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                color= Color.DarkGray)
            Text(text ="20%",
                color= Color(red = 255,green=1047,blue=104)
            )
            iconDecrease()
        }
        Text(text = "与健康久坐时间相比",
            color= Color(red =198,green=199,blue=209),
            fontSize = 12.sp
        )
    }

}


//喝水次数卡片---------------------------------------------------
@Composable
fun WaterFrame()
{
    Box (contentAlignment = Alignment.TopCenter){
        ImgWaterFrame()
        Column(modifier = Modifier.padding(top=60.dp)) {
            Row (horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp)){
                AverageWaterInf()
                CompareWaterInf()
            }
            BarChart()
            Spacer(modifier =Modifier.height(58.dp))
        }

    }
}


@Composable
fun ImgWaterFrame(){
    Image(
        painter = painterResource(id = R.drawable.g6_1_card_waternum),
        contentDescription = null,
    )

}
@Composable
fun AverageWaterInf()
{
    Column() {
        Row (horizontalArrangement = Arrangement.spacedBy(7.dp), verticalAlignment = Alignment.Bottom){
            Text(text = "5次 ",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color= Color.DarkGray)
            Text(text ="194%",
                color= Color(red = 26,green=207,blue=163)
            )
            iconIncrease()
        }
        Text(text = "平均喝水次数",
            color= Color(red =198,green=199,blue=209)
        )
    }

}
@Composable
fun CompareWaterInf()
{
    Column() {
        Row (horizontalArrangement = Arrangement.spacedBy(7.dp), verticalAlignment = Alignment.Bottom){
            Text(text = "58:20 ",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color= Color.DarkGray)
            Text(text ="30%",
                color= Color(red = 255,green=1047,blue=104)
            )
            iconDecrease()
        }
        Text(text = "与健康喝水次数相比",
            color= Color(red =198,green=199,blue=209)
        )
    }

}




@Composable
fun EatFrame()
{
    Box (contentAlignment = Alignment.TopCenter){
        ImgEatFrame()
        Column(modifier = Modifier.padding(top=60.dp)) {

            BarChart()
            Spacer(modifier =Modifier.height(58.dp))
        }

    }
}

@Composable
fun ImgEatFrame(){
    Image(
        painter = painterResource(id = R.drawable.g6_1_card_eatnum),
        contentDescription = null,
    )

}









@Composable
fun iconIncrease(){
    Image(
        modifier=Modifier.size(16.dp),
        painter = painterResource(id = R.drawable.g6_1_ic_increase),
        contentDescription = null,

        )

}
@Composable
fun iconDecrease(){
    Image(
        modifier=Modifier.size(16.dp),
        painter = painterResource(id = R.drawable.g6_1_ic_decrease),
        contentDescription = null,

        )

}

@Composable

/*
* 顶部菜单栏 公共组件案例
* 复制修改即可使用
* (注意：TopAppBar必须放在Scaffold的topBar属性下，详细可参考IslandChooseIslandScreen.kt)
* */

fun HealthTopAppBar() {
    androidx.compose.material.TopAppBar(title = {
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
                modifier = Modifier.offset(-35.dp, 0.dp)//向左偏移一段距离
            )
        }
    },
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        elevation = 0.dp, //设置阴影
        //左侧按钮
        navigationIcon = {

                         IconButton(onClick = {}) {
                              Icon(
                                    Icons.Default.ArrowBack,
                                    contentDescription = "",
                                )
                           }
        },
        //右侧按钮
        actions = {
//                            IconButton(onClick = {}) {
//                                Icon(
//                                    Icons.Default.Settings,
//                                    contentDescription = "",
//                                )
//                            }
        }
    )
}


//    val showMenu = remember {
//        mutableStateOf(false)
//    }

//    TopAppBar(
//        title = {
//            Text(text =, color = Color.White)
//        },
//        backgroundColor = MaterialTheme.colors.primary,
//        navigationIcon = {
//            IconButton(onClick = {}) {
//                Icon(Icons.Default.Menu, contentDescription = "", tint = Color.White)
//            }
//        },
//        actions = {
//            IconButton(onClick = {}) {
//                Icon(Icons.Default.Settings, contentDescription = "", tint = Color.White)
//            }
//            IconButton(onClick = { showMenu.value = !showMenu.value }) {
//                Icon(Icons.Default.MoreVert, contentDescription = "", tint = Color.White)
//            }
//
//            DropdownMenu(expanded = showMenu.value, onDismissRequest = { showMenu.value = false }) {
//                DropdownMenuItem(onClick = {}) {
//                    TextButton(onClick = { showMenu.value = false }) {
//                        Text(text = "Settings", color = MaterialTheme.colors.primary)
//                    }
//                }
//                DropdownMenuItem(onClick = {}) {
//                    TextButton(onClick = { showMenu.value = false }) {
//                        Text(text = "Search", color = MaterialTheme.colors.primary)
//                    }
//                }
//            }
//        }
//    )

@Composable
fun ImgPlant1(){
    Image(
        painter = painterResource(id = R.drawable.g6_1_img_flower),
        contentDescription = null,
    )

}

@Composable
fun InformationRow(){
    Column(verticalArrangement = Arrangement.spacedBy(7.dp)){
        Text(text = "本周植物：向白葵", fontSize = 14.sp, color = Color(73,74,89), fontWeight = FontWeight.W700)
        Text(text = "种植时段：10.6-10.13", fontSize = 14.sp, color = Color(73,74,89), fontWeight = FontWeight.W700)
        Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
            Text(text = "成熟期", fontSize = 14.sp, color = Color(73,74,89), fontWeight = FontWeight.W700)
            Spacer(modifier = Modifier.width(10.dp))
            ProgressBar(
                modifier = Modifier
                    .width(90.dp) // 指定进度条宽度
                    .height(6.dp), // 指定进度条高度
                progress = 0.8f,
                color = Color(26,207,163),
                cornerRadius = 12.dp,
                backgroundColor = Color.White
            )
        }
    }
}

@Composable
fun BtnGetConclusion(){

}
@Composable
fun ImgBtnGet(){
    Image(
        painter = painterResource(id = R.drawable.g6_1_btn_getconclusion),
        contentDescription = null,
    )

}


@Composable
fun HealthViewTabRow() {

    var state by remember { mutableStateOf(0) }
    val titles = listOf("本周", "过往")
    Column {
        androidx.compose.material.TabRow(
            selectedTabIndex = state,
            indicator = @Composable { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.customTabIndicatorOffset(tabPositions[state]),
                    color = Color(26,207,163)
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
}

fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = currentTabPosition.width,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset + currentTabWidth / 4)  // 偏移量加上宽度的1/4
        .width(currentTabWidth / 2)  // 宽度Tab宽度的1/2
        .height(4.dp)

}

@Composable
fun ImgAdviceFrame(){
    Image(modifier=Modifier.fillMaxWidth(),
        painter = painterResource(id = R.drawable.g6_1_card_advice),
        contentDescription = null,
    )

}


//-----------------------tab栏与viewpager------------------------------






