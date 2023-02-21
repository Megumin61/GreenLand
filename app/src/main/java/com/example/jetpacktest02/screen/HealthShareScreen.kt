///*
// * Copyright 2022 The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.example.jetpacktest02.ui.main
//
//import android.annotation.SuppressLint
//import android.bluetooth.BluetoothGatt
//import android.graphics.Point
//import android.util.Log
//import android.widget.ProgressBar
//import android.widget.Space
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.core.FloatTweenSpec
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.slideInVertically
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.gestures.detectTapGestures
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Favorite
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material3.*
//import androidx.compose.ui.Modifier
//
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.draw.DrawModifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.drawWithContent
//import androidx.compose.ui.geometry.CornerRadius
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.graphics.drawscope.ContentDrawScope
//import androidx.compose.ui.graphics.drawscope.DrawScope
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.platform.InspectorInfo
//import androidx.compose.ui.platform.InspectorValueInfo
//import androidx.compose.ui.platform.debugInspectorInfo
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.jetpacktest02.R
//import com.example.jetpacktest02.compose.MyTopAppBar
//import com.example.scaffolddemo.ui.theme.Green1
//import com.example.scaffolddemo.ui.theme.Green2
//import com.google.accompanist.systemuicontroller.rememberSystemUiController
//import com.google.accompanist.systemuicontroller.rememberSystemUiController
//import kotlinx.coroutines.delay
//import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
//
///**
// * The Bills screen.
// */
//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Preview(showBackground=true,widthDp=393,heightDp=851)
//@Composable
//fun HealthShareScreen(
////            bills : (String) -> Unit = {},
//    nav01: () -> Unit = {},
//
//    ) {
//    var animVisible by remember {
//        mutableStateOf(false)
//    }
//    LaunchedEffect(key1 = 1) {
//        delay(200)
//        animVisible = true
//    }
//    AnimatedVisibility(
//        visible = animVisible,
//        enter = fadeIn(initialAlpha = 0.3f) + slideInVertically(
//            initialOffsetY = { 800 },
//            animationSpec = tween(durationMillis = 1200)
//        )
//    ) {
//
//
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Box(
//                modifier = Modifier.padding(top = 60.dp), contentAlignment = Alignment.BottomCenter
//            ) {
//                ImgBg()
//                Text(
//                    "9.29-10.5",
//                    modifier = Modifier.padding(bottom = 540.dp),
//                    color = Color(73, 73, 89)
//                )
//                Column() {
//                    UpperArea()
//                    Spacer(modifier = Modifier.height(380.dp))
//                }
//
//                Column(
//                    modifier = Modifier.padding(start = 65.dp, end = 70.dp),
//                    horizontalAlignment = Alignment.Start,
//                    verticalArrangement = Arrangement.spacedBy(20.dp)
//                ) {
//                    ProgressStep()
//                    ProgressSit()
//                    ProgressWater()
//                    ProgressEat()
//                    Spacer(modifier = Modifier.height(170.dp))
//                }
//
//<<<<<<< HEAD
//) {
//    rememberSystemUiController().setStatusBarColor(
//        Color.White, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
//    )
//
//    Surface() {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier
//                .fillMaxSize()
//                .background(
//                    brush = Brush.verticalGradient(
//                        colors = listOf(
//                            Green1,
//                            Green2
//                        )
//                    )
//                )
//        ){ androidx.compose.material.Scaffold(
//            topBar = {
//                androidx.compose.material.TopAppBar(
//                    title = {
//                        Box(
//                            modifier = Modifier.fillMaxWidth(),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text(
//                                text = "健康总结",
//                                style = TextStyle(
//                                    fontWeight = FontWeight.W900, //设置字体粗细
//                                    fontSize = 18.sp,
//                                ),
//                                modifier = Modifier.offset(-25.dp, 0.dp)//向左偏移一段距离
//                            )
//                        }
//                    },
//                    //左侧按钮
//                    navigationIcon = {
//                        IconButton(onClick = nav01) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.g1_2_0_ic_arrow_left),
//                                contentDescription = ""
//                            )
//
//                        }
//                    },
//                    //右侧按钮
//                    actions = {
////                    Image(
////                        painter = painterResource(id = R.drawable.g2_5_btn_friend),
////                        contentDescription = null,
////                        modifier = Modifier
////                            .width(100.dp)
////                            .height(100.dp)
////                            .offset(-10.dp, 0.dp)
//////                            .clickable(onClick = {userViewModel.uiState.value.pageState.value=3})
////                    )
//                    },
//
//                    backgroundColor = Color.Transparent,
//                    contentColor = Color.Black,
//                    elevation = 0.dp, //设置阴影
//                )
//            }
//        ){
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Box(
//                    modifier = Modifier
//                        .size(393.dp, 851.dp)
//                        .background(
//                            brush = Brush.verticalGradient(
//                                listOf(
//                                    Color(221, 247, 244),
//                                    Color(241, 246, 241)
//                                )
//                            )
//                        )
//                , contentAlignment = Alignment.TopCenter
//                ){
//                    Image(
//                        painter = painterResource(id = R.drawable.g6_3_card03),
//                        contentDescription = null,
//                    )
//                    Image(
//                        painter = painterResource(id = R.drawable.g6_3_card02),
//                        contentDescription = null,
//                    )
//                    Box(contentAlignment = Alignment.Center) {
//                        Image(
//                            painter = painterResource(id = R.drawable.g6_3_card01),
//                            contentDescription = null,
//                        )
//
//                        /*Text("9.29-10.5", modifier = Modifier.padding(bottom = 540.dp),color=Color(73,73,89))*/
//                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//=======
//            }
//            Spacer(modifier = Modifier.height(10.dp))
//            RowOfBtns()
//
//        }
//>>>>>>> main
//
//                            UpperArea()
//                            Spacer(modifier = Modifier.height(70.dp))
//                            Column(
//                                modifier = Modifier.padding(start = 70.dp, end = 70.dp),
//                                horizontalAlignment = Alignment.Start,
//                                verticalArrangement = Arrangement.spacedBy(20.dp)
//                            ) {
//                                ProgressStep()
//                                ProgressSit()
//                                ProgressWater()
//                                ProgressEat()
//
//                            }
//
//
//                        }
//                    }
//                    Box(modifier = Modifier.height(851.dp), contentAlignment = Alignment.Center){
//                        Column(verticalArrangement = Arrangement.SpaceBetween,
//                        modifier = Modifier.fillMaxHeight(),
//                        horizontalAlignment = Alignment.CenterHorizontally) {
//                            Row(
//                                verticalAlignment = Alignment.CenterVertically,
//
//                                modifier = Modifier.padding(top = 14.dp)
//                            ) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.userprofile_1),
//                                    contentDescription = null,
//                                    modifier = Modifier.size(52.dp)
//                                )
//                                Column() {
//                                    Text(
//                                        text = "@skcc", fontSize = 26.sp, color = Color(68, 91, 96),
//                                        fontWeight = FontWeight.W800
//                                    )
//                                    Text(
//                                        text = "2022 9/29-10/5",
//                                        fontSize = 14.sp,
//                                        color = Color(73, 74, 89),
//                                    )
//                                }
//                            }
//                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//
//                                Image(
//                                    painter = painterResource(id = R.drawable.g6_3_tips),
//                                    contentDescription = null,
//                                )
//                                Spacer(modifier = Modifier.height(10.dp))
//                                RowOfBtns()
//                            }
//
//                        }
//
//
//                    }
//
//
//
//                }
//
//
//            }
//        }}
//    }
//
//
//<<<<<<< HEAD
//
//
//
//=======
//>>>>>>> main
//}
//
//
////进度条-------------------------------------------------------------------------------------
////------------------------------------------------------------------------------------------
//@Composable
//fun ProgressBar(
//    modifier: Modifier,
//    progress: Float,
//    color: Color,
//    cornerRadius: Dp,
//    backgroundColor: Color,
//) {
//    Surface(
//        shape = RoundedCornerShape(cornerRadius),
//        color = backgroundColor,
//        modifier = modifier
//            .clip(RoundedCornerShape(cornerRadius)) // 裁剪矩形区域为圆角矩形，将超出圆角矩形的部分绘制去掉
//            .drawWithContent {
//                drawContent() // 先绘制内容后绘制自定义图形，这样我们绘制的图形将显示在内容区域上方
//                val progressWidth = drawContext.size.width * progress
//
//                drawRoundRect(
//                    color = color,
//
//                    size = drawContext.size.copy(width = progressWidth),
//                    cornerRadius = CornerRadius(30f, 30f)
//                )
//            },
//        content = {}
//    )
//}
//
///**
// * Draw into a [Canvas] behind the modified content.
// */
//fun Modifier.drawBehind(
//    onDraw: DrawScope.() -> Unit
//) = this.then(
//    DrawBackgroundModifier(
//        onDraw = onDraw,
//        inspectorInfo = debugInspectorInfo {
//            name = "drawBehind"
//            properties["onDraw"] = onDraw
//        }
//    )
//)
//
//private class DrawBackgroundModifier(
//    val onDraw: DrawScope.() -> Unit,
//    inspectorInfo: InspectorInfo.() -> Unit
//) : DrawModifier, InspectorValueInfo(inspectorInfo) {
//
//    override fun ContentDrawScope.draw() {
//        onDraw()
//        drawContent() // 在绘制图形后绘制内容，也就是我们绘制的图形会显示在内容区域之后（下方）
//    }
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (other !is DrawBackgroundModifier) return false
//
//        return onDraw == other.onDraw
//    }
//
//    override fun hashCode(): Int {
//        return onDraw.hashCode()
//    }
//}
//
//
////图片------------------------------------
//@Composable
//fun ImgBg() {
//    Image(
//        painter = painterResource(id = R.drawable.g6_3_bg),
//        contentDescription = null,
//    )
//
//}
//
//@Composable
//fun ImgPlant() {
//    Image(
//        painter = painterResource(id = R.drawable.g6_3_img_flower),
//        contentDescription = null,
//    )
//
//}
//
//
////按钮----------------------------------------------------------------------------------------------
//@Composable
//fun BtnWechat() {
//    Button(
//        onClick = { /*TODO*/ },
//        colors = ButtonDefaults.outlinedButtonColors(),
//        contentPadding = PaddingValues(0.dp)
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.g6_3_ic_wechat),
//            contentDescription = null,
//        )
//    }
//
//
//}
//
//@Composable
//fun BtnMoment() {
//    Button(
//        onClick = { /*TODO*/ },
//        colors = ButtonDefaults.outlinedButtonColors(),
//        contentPadding = PaddingValues(0.dp)
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.g6_3_ic_moment),
//            contentDescription = null,
//        )
//    }
//
//}
//
//@Composable
//fun BtnQQ() {
//    Button(
//        onClick = { /*TODO*/ },
//        colors = ButtonDefaults.outlinedButtonColors(),
//        contentPadding = PaddingValues(0.dp)
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.g6_3_ic_qq),
//            contentDescription = null,
//        )
//    }
//
//}
//
//@Composable
//fun BtnPhoto() {
//    Button(
//        onClick = { /*TODO*/ },
//        colors = ButtonDefaults.outlinedButtonColors(),
//        contentPadding = PaddingValues(0.dp)
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.g6_3_ic_photo),
//            contentDescription = null,
//        )
//    }
//}
//
//@Composable//按钮列
//<<<<<<< HEAD
//fun RowOfBtns(){
//    Row(horizontalArrangement = Arrangement.spacedBy(28.dp), modifier = Modifier.padding(bottom = 20.dp)) {
//=======
//fun RowOfBtns() {
//    Row(horizontalArrangement = Arrangement.spacedBy(28.dp)) {
//>>>>>>> main
//        BtnWechat()
//        BtnMoment()
//        BtnQQ()
//        BtnPhoto()
//    }
//}
//
//@Composable
//fun ProgressStep() {
//    Row(
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.spacedBy(5.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "平均步数 ",
//                fontSize = 11.sp,
//                fontWeight = FontWeight.W500,
//                color = Color.DarkGray
//            )
//            ProgressBar(
//                modifier = Modifier
//                    .width(130.dp) // 指定进度条宽度
//                    .height(12.dp), // 指定进度条高度
//                progress = 0.6f,
//                color = Color(207, 235, 223),
//                cornerRadius = 12.dp,
//                backgroundColor = Color.Transparent
//            )
//        }
//
//        Text(
//            text = "8342 ",
//            fontSize = 12.sp,
//            fontWeight = FontWeight.W900,
//            color = Color.DarkGray
//        )
//        iconIncrease()
//
//    }
//}
//
//@Composable
//fun ProgressSit() {
//    Row(
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.spacedBy(5.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "久坐时间 ",
//                fontSize = 11.sp,
//                fontWeight = FontWeight.W500,
//                color = Color.DarkGray
//            )
//            ProgressBar(
//                modifier = Modifier
//                    .width(130.dp) // 指定进度条宽度
//                    .height(12.dp), // 指定进度条高度
//                progress = 0.8f,
//                color = Color(255, 226, 194),
//                cornerRadius = 12.dp,
//                backgroundColor = Color.Transparent
//            )
//        }
//
//        Text(
//            text = "6:20:12 ",
//            fontSize = 12.sp,
//            fontWeight = FontWeight.W900,
//            color = Color.DarkGray
//        )
//        iconDecrease()
//
//    }
//}
//
//@Composable
//fun ProgressWater() {
//    Row(
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.spacedBy(5.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "喝水次数 ",
//                fontSize = 11.sp,
//                fontWeight = FontWeight.W500,
//                color = Color.DarkGray
//            )
//            ProgressBar(
//                modifier = Modifier
//                    .width(130.dp) // 指定进度条宽度
//                    .height(12.dp), // 指定进度条高度
//                progress = 0.7f,
//                color = Color(207, 235, 223),
//                cornerRadius = 12.dp,
//                backgroundColor = Color.Transparent
//            )
//        }
//
//        Text(
//            text = "7次 ",
//            fontSize = 12.sp,
//            fontWeight = FontWeight.W900,
//            color = Color.DarkGray
//        )
//        iconIncrease()
//
//    }
//}
//
//@Composable
//fun ProgressEat() {
//    Row(
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.spacedBy(5.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "按时吃饭 ",
//                fontSize = 11.sp,
//                fontWeight = FontWeight.W500,
//                color = Color.DarkGray
//            )
//            ProgressBar(
//                modifier = Modifier
//                    .width(130.dp) // 指定进度条宽度
//                    .height(12.dp), // 指定进度条高度
//                progress = 0.5f,
//                color = Color(207, 235, 223),
//                cornerRadius = 12.dp,
//                backgroundColor = Color.Transparent
//            )
//        }
//
//        Text(
//            text = "2.5次 ",
//            fontSize = 12.sp,
//            fontWeight = FontWeight.W900,
//            color = Color.DarkGray
//        )
//        iconIncrease()
//
//    }
//}
//
//@Composable
//<<<<<<< HEAD
//fun ThreeParameterColumn(){
//    Column(
//        verticalArrangement = Arrangement.spacedBy(8.dp)) {
//        Box(contentAlignment = Alignment.BottomStart) {
//            Image(
//                painter = painterResource(id = R.drawable.g6_3_mood),
//                contentDescription = null,
//            )
//            Text(text = "79",
//                fontSize = 10.sp,
//                color = Color(184, 192, 194),
//            modifier = Modifier.padding(bottom = 5.dp,start =50.dp))
//        }
//        Box(contentAlignment = Alignment.BottomStart) {
//            Image(
//                painter = painterResource(id = R.drawable.g6_3_water),
//                contentDescription = null,
//            )
//            Text(text = "372g", fontSize = 10.sp, color = Color(184, 192, 194),
//                modifier = Modifier.padding(bottom = 5.dp, start =50.dp))
//        }
//        Box(contentAlignment = Alignment.BottomStart) {
//            Image(
//                painter = painterResource(id = R.drawable.g6_3_energy),
//                contentDescription = null,
//            )
//            Text(text = "70", fontSize = 10.sp, color = Color(184, 192, 194),
//                modifier = Modifier.padding(bottom = 5.dp,start =50.dp))
//        }
//=======
//fun ThreeParameterColumn() {
//    Column(
//        modifier = Modifier.padding(end = 20.dp, top = 37.dp),
//        verticalArrangement = Arrangement.spacedBy(33.dp)
//    ) {
//        Text(text = "78", fontSize = 10.sp, color = Color(184, 192, 194))
//        Text(text = "372g", fontSize = 10.sp, color = Color(184, 192, 194))
//        Text(text = "70", fontSize = 10.sp, color = Color(184, 192, 194))
//>>>>>>> main
//    }
//}
//
//
//@Composable
//fun ProgressPlant() {
//    Row(
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.spacedBy(5.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//
//            ProgressBar(
//                modifier = Modifier
//                    .width(90.dp) // 指定进度条宽度
//                    .height(6.dp), // 指定进度条高度
//                progress = 0.8f,
//                color = Color(26, 207, 163),
//                cornerRadius = 12.dp,
//                backgroundColor = Color.White
//            )
//        }
//
//
//    }
//}
//
//@Composable
//<<<<<<< HEAD
//fun PlantFrame(){
//    Column(horizontalAlignment = Alignment.CenterHorizontally,
//    verticalArrangement = Arrangement.spacedBy(10.dp)) {
//        Box(contentAlignment = Alignment.Center){
//            Image(
//                painter = painterResource(id = R.drawable.g6_3_round),
//                contentDescription = null,
//            )
//            ImgPlant()
//        }
//
//=======
//fun PlantFrame() {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(10.dp)
//    ) {
//        ImgPlant()
//>>>>>>> main
//        ProgressPlant()
//
//    }
//}
//
//@Composable
//fun UpperArea() {
//    Row {
//        PlantFrame()
//        Spacer(modifier = Modifier.width(28.dp))
//        ThreeParameterColumn()
//    }
//}