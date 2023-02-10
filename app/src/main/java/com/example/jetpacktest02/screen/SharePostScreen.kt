package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ui.main.ProgressBar
import com.example.jetpacktest02.ui.main.iconDecrease
import com.example.jetpacktest02.ui.main.iconIncrease
import com.example.scaffolddemo.ui.theme.Green1
import com.example.scaffolddemo.ui.theme.Green2
import com.example.scaffolddemo.ui.theme.Green4
import com.example.scaffolddemo.ui.theme.LightGreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay


@Composable
fun ProgressStep(){
    Row (
        verticalAlignment = Alignment.CenterVertically){
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)
            , verticalAlignment = Alignment.CenterVertically){
            ProgressBar(
                modifier = Modifier
                    .width(130.dp) // 指定进度条宽度
                    .height(12.dp), // 指定进度条高度
                progress = 0.6f,
                color = Color(207,235,223),
                cornerRadius = 12.dp,
                backgroundColor = Color.Transparent
            )
        }

        Text(text = "8342 ",
            fontSize = 12.sp,
            fontWeight = FontWeight.W900,
            color= Color.DarkGray)
        iconIncrease()

    }
}
@Composable
fun ProgressSit(){
    Row (
        verticalAlignment = Alignment.CenterVertically){
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)
            , verticalAlignment = Alignment.CenterVertically){
            ProgressBar(
                modifier = Modifier
                    .width(130.dp) // 指定进度条宽度
                    .height(12.dp), // 指定进度条高度
                progress = 0.8f,
                color = Color(255,226,194),
                cornerRadius = 12.dp,
                backgroundColor = Color.Transparent
            )
        }

        Text(text = "6:20:12 ",
            fontSize = 12.sp,
            fontWeight = FontWeight.W900,
            color= Color.DarkGray)
        iconDecrease()

    }
}
@Composable
fun ProgressWater(){
    Row (
        verticalAlignment = Alignment.CenterVertically){
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)
            , verticalAlignment = Alignment.CenterVertically){
            ProgressBar(
                modifier = Modifier
                    .width(130.dp) // 指定进度条宽度
                    .height(12.dp), // 指定进度条高度
                progress = 0.7f,
                color = Color(207,235,223),
                cornerRadius = 12.dp,
                backgroundColor = Color.Transparent
            )
        }

        Text(text = "7次 ",
            fontSize = 12.sp,
            fontWeight = FontWeight.W900,
            color= Color.DarkGray)
        iconIncrease()

    }
}
@Composable
fun ProgressEat(){
    Row (
        verticalAlignment = Alignment.CenterVertically){
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)
            , verticalAlignment = Alignment.CenterVertically){
            ProgressBar(
                modifier = Modifier
                    .width(130.dp) // 指定进度条宽度
                    .height(12.dp), // 指定进度条高度
                progress = 0.5f,
                color = Color(207,235,223),
                cornerRadius = 12.dp,
                backgroundColor = Color.Transparent
            )
        }

        Text(text = "2.5次 ",
            fontSize = 12.sp,
            fontWeight = FontWeight.W900,
            color= Color.DarkGray)
        iconIncrease()

    }
}

@Composable
fun ProgressPlant(){
    Row (
        verticalAlignment = Alignment.CenterVertically){
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)
            , verticalAlignment = Alignment.CenterVertically){

            ProgressBar(
                modifier = Modifier
                    .width(90.dp) // 指定进度条宽度
                    .height(6.dp)
                    .offset(85.dp,270.dp), // 指定进度条高度
                progress = 0.8f,
                color = Color(26,207,163),
                cornerRadius = 12.dp,
                backgroundColor = Color.White
            )
        }
    }
    Row(modifier = Modifier.offset(108.dp,280.dp)) {
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
@Composable
fun ThreeParameterColumn(){
    Column(modifier=Modifier.padding(end = 20.dp,top=37.dp).offset(261.dp,136.dp),
        verticalArrangement = Arrangement.spacedBy(33.dp)) {
        Text(text = "78", fontSize = 10.sp, color = Color(184,192,194))
        Text(text = "372g", fontSize = 10.sp, color = Color(184,192,194))
        Text(text = "70", fontSize = 10.sp, color = Color(184,192,194))
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun SharePostScreen(){

    var animVisible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = 1) {
        delay(200)
        animVisible = true
    }
    rememberSystemUiController().setStatusBarColor(
        Green1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )
    Surface(modifier = Modifier.fillMaxSize()){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Green1,
                            Green2
                        )
                    )
                )
        ){
            Scaffold(
                topBar = {
                    TopAppBar(title = {
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
                        backgroundColor = Green1,
                        contentColor = Color.Black,
                        elevation = 0.dp, //设置阴影
                        //左侧按钮
                        navigationIcon = {

                            IconButton(onClick = {}) {
                                Icon(
                                    bitmap = ImageBitmap.imageResource(id = R.drawable.g1_2_0_ic_arrow_left),
                                    contentDescription = null
                                ) }
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
            ) {


                var scrollState = rememberScrollState()


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Green1,
                                    Green2
                                )
                            )
                        )

                ) {

                    androidx.compose.animation.AnimatedVisibility(
                        visible = animVisible,
                        enter = fadeIn(initialAlpha = 0.3f) + slideInVertically(
                            initialOffsetY = { 800 },
                            animationSpec = tween(durationMillis = 1200)
                        )
                    ) {

                        Box(Modifier.fillMaxSize()) {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.g6_ic_skcc_pic),
                                    contentDescription = null
                                )
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.offset(0.dp, 5.dp)
                                ) {
                                    Text(
                                        text = "@skcc", fontSize = 26.sp,
                                        fontWeight = FontWeight.W900,
                                        style = MaterialTheme.typography.labelLarge,
                                        color = Green4,
                                        textAlign = TextAlign.Justify
                                    )
                                    Text(
                                        text = "2022 9/29-10/5", fontSize = 12.sp,
                                        color = Green4,
                                        textAlign = TextAlign.Justify
                                    )
                                }
                            }
                            Column(
                                Modifier.fillMaxSize().offset(0.dp, 80.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.g6_ic_skcc_sharepost),
                                    contentDescription = null,
                                    modifier = Modifier.scale(1.05f)
                                )

                            }
                            ProgressPlant()
                            ThreeParameterColumn()
                            Column(
                                modifier = Modifier.padding(start = 65.dp, end = 70.dp)
                                    .offset(60.dp, 350.dp),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                ProgressStep()
                                ProgressSit()
                                ProgressWater()
                                ProgressEat()
                                Spacer(modifier = Modifier.height(170.dp))
                            }
                        }
                    }
                    }
                }}}

}