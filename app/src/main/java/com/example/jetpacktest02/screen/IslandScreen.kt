package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.text.font.FontWeight.Companion.W800
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.airbnb.lottie.compose.*
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IslandScreen(
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},
    nav03: () -> Unit = {},
    nav04: () -> Unit = {},
) {
    var msgVisible by remember {
        mutableStateOf(false)
    }

    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Flesh2, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )


    Surface(modifier = Modifier.fillMaxSize()) {
        //顶部菜单栏
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "好友岛",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //设置字体粗细
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-35.dp, 0.dp)//向左偏移一段距离
                        )
                    }
                },
                    backgroundColor = Flesh2,
                    contentColor = Color.Black,
                    elevation = 0.dp, //设置阴影
                    //左侧按钮
                    navigationIcon = {

                        IconButton(onClick = nav01) {
                            Icon(
                                bitmap = ImageBitmap.imageResource(id = R.drawable.g1_2_0_ic_arrow_left),
                                contentDescription = "",
                            )
                        }
                    },
                    //右侧按钮
                    actions = {
                    }

                )
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Flesh2,
                                Flesh1
                            )
                        )
                    )
            ) {
                //好友列表按钮
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.g4_2_ic_friendlist),
                        contentDescription = null,
                        modifier = Modifier
                            .size(55.dp)
                            .offset(-15.dp, 0.dp)
                            .clickable(
                                onClick = nav02,
                                indication = null,
                                interactionSource = MutableInteractionSource()
                            )
                    )
                }
                //天气顶图
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = -80.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.g4_2_bg_weather),
                        contentDescription = null,
                        modifier = Modifier
                            .width(270.dp)
                    )
                }

                Box(modifier = Modifier.offset(y = -40.dp)) {
                    //弹出的消息小卡片
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = -70.dp)
                    ) {
                        AnimatedVisibility(
                            visible = msgVisible,
                            enter = slideInVertically(initialOffsetY = { 100 }) + fadeIn(
                                initialAlpha = 0.3f
                            )
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp)
                                    .padding(horizontal = 60.dp) // 外边距
                                ,
                                backgroundColor = Color(0xfff5f5f7),
                                shape = MaterialTheme.shapes.medium,
                                elevation = 1.5.dp

                            ) {
                                //上方区域
                                Column(
                                    //设置内边距
                                    modifier = Modifier.padding(
                                        start = 0.dp,
                                        end = 0.dp,
                                        top = 10.dp,
                                        bottom = 30.dp
                                    )
                                ) {
                                    //文字输入区
                                    Row(modifier = Modifier) {
                                        var text by remember { mutableStateOf("大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！") }
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(Color(0xfff5f5f7)),
                                            contentAlignment = Alignment.TopStart
                                        ) {
                                            BasicTextField(readOnly = true,
                                                value = text,
                                                onValueChange = {
                                                    text = it
                                                },
                                                textStyle = TextStyle(
                                                    color = textGray,
                                                    fontWeight = W800
                                                ),
                                                modifier = Modifier
                                                    .background(Color(0xfff5f5f7), CircleShape)
                                                    .fillMaxWidth(),
                                                decorationBox = { innerTextField ->
                                                    Row(
                                                        verticalAlignment = Alignment.CenterVertically,
                                                        modifier = Modifier.padding(horizontal = 10.dp)
                                                    ) {
//                                            IconButton(
//                                                onClick = { }
//                                            ) {
//                                                Icon(painterResource(id = R.drawable.g4_2_ic_friendlist), null)
//                                            }
                                                        Box(
                                                            modifier = Modifier
                                                                .weight(1f)
                                                                .fillMaxSize(),
                                                            contentAlignment = Alignment.TopStart
                                                        ) {
                                                            innerTextField()
                                                        }
//                                            IconButton(
//                                                onClick = { },
//                                            ) {
//                                                Icon(Icons.Filled.Send, null)
//                                            }
                                                    }
                                                }
                                            )
                                        }
                                    }
                                }

                                //下方区域
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(vertical = 9.dp, horizontal = 16.dp),
                                    verticalArrangement = Arrangement.Bottom
                                ) {
                                    //消息发送时间标注
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            buildAnnotatedString {
                                                append("sandro")
                                            },
                                            fontSize = 12.sp,
                                            color = Gray1
                                        )
                                        Text(
                                            buildAnnotatedString {
//                            withStyle(
//                                style = SpanStyle(
//                                    fontWeight = FontWeight.W600,
//                                    fontSize = 12.sp,
//                                    color = Color(0xFF4552B8)
//                                )
//                            ) {
//                                append("20")
//                            }
                                                append("3分钟前")
                                            },
                                            fontSize = 12.sp,
                                            color = Gray1
                                        )
                                    }
                                }


                            }
                        }


                    }

                    // 地图扫描动画背景
                    MapBgAnimation({ msgVisible = true },nav03,nav04)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = 400.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.g4_2_btn_publish),
                            contentDescription = null,
                            modifier = Modifier
                                .height(60.dp)
                                .clickable(
                                    onClick = nav03,
                                    indication = null,
                                    interactionSource = MutableInteractionSource()
                                )
                        )
                    }

                }


            }
        }

    }

}


@Composable
fun plantModelItem(
    username: String,
    plantType: Int,
    offsetX: Float = 0f,
    offsetY: Float = 0f,
    hasMsg: Boolean,
    showMsg: () -> Unit = {},
    nav:() -> Unit = {},
    nav2:()->Unit={}
) {
    Column(
        modifier = Modifier.offset(offsetX * 100.dp, offsetY * 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier, contentAlignment = Alignment.TopEnd) {
            //右上角消息小红点
            if (hasMsg) {
                Row(modifier = Modifier.padding(6.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.g4_2_ic_msgdot),
                        contentDescription = null,
                        modifier = Modifier
                            .size(7.dp)
                            .clickable(
                                onClick = showMsg
                            )
                    )
                }
            } else {
            }

            //植物图片
            if (hasMsg) {
                Image(
                    painter = painterResource(id = plantType),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp).clickable(onClick = nav2)
                )
            } else {
                Image(
                    painter = painterResource(id = plantType),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp).clickable(onClick = nav2)
                )
            }

        }
        //用户名
        Text(
            text = username,
            style = TextStyle(
                fontWeight = FontWeight.W500, //设置字体粗细
                fontSize = 12.sp,
            ),
            modifier = Modifier
        )
    }

}

@Composable
fun MapBgAnimation(showMsg: () -> Unit = {},nav:()->Unit={},nav2:()->Unit={}) {

    //控制播放
    var isPlaying by remember {
        mutableStateOf(true)
    }
    //控制播放速度
    var speed by remember {
        mutableStateOf(1f)
    }
    LaunchedEffect(1) {
        delay(2000)
        isPlaying = false
    }
    //控制循环播放次数 累加该值可以增加播放次数
    var interations by remember {
        mutableStateOf(1)
    }
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("animations/map2.json"))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = interations,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = true  // 暂停后重新播放是否从头开始
    )

    Box(modifier = Modifier.offset(y = -80.dp), contentAlignment = Alignment.Center) {

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .size(450.dp)
                .alpha(if (isPlaying == false) 0f else 1f)
        )
        plantModelItem("ajunGrit", R.drawable.g4_2_img_flower_shadowed, 0f, 0f, false, showMsg,nav2)
        plantModelItem("sancho", R.drawable.g4_2_img_cactus_shadowed, -1.2f, 1.3f, true, showMsg,nav2)
        plantModelItem("megumin", R.drawable.g4_2_img_cactus_shadowed, 1f, 1f, false, showMsg,nav2)
        plantModelItem("skccc", R.drawable.g4_2_img_grass_shadowed, -1f, -0.7f, false, showMsg,nav2)
        plantModelItem("foxbread", R.drawable.g4_2_img_flower_shadowed, 0.7f, -1.0f, false, showMsg,nav2)
    }


}

@Preview("Light Mode")
@Composable
fun DefaultPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Flesh1,
                        Green3
                    )
                )
            )
    ) {

    }

}