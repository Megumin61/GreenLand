package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IslandExploreScreen(
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},
    nav03: () -> Unit = {},
    nav04: () -> Unit = {},
) {
    var showImgDialog by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = showImgDialog) {
        delay(5000)
        showImgDialog = false
    }

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
                            text = "探索岛",
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
                        painter = painterResource(id = R.drawable.g4_2_ic_nearbylist),
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
                        .offset(y = -65.dp),
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
                                                    fontWeight = FontWeight.W800
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
                    MapBgAnimation({ msgVisible = true }, nav03, nav04, { showImgDialog = true })
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

            //图片消息弹窗
            if (showImgDialog) {
                Dialog(
                    onDismissRequest = { showImgDialog = false },
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //头像、用户名、好友标签、发布时间
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            //头像、用户名、好友标签
                            Row(
                                modifier = Modifier,
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.g4_3_avatar1),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "skccc",
                                    style = TextStyle(
                                        fontWeight = FontWeight.W600, //设置字体粗细
                                        fontSize = 18.sp,
                                        color = Color.White
                                    ),
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.g4_2_ic_friendtag),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(40.dp)
                                        .clickable { }
                                )
                            }

                            //发布时间
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "22分钟前",
                                    style = TextStyle(
                                        fontWeight = FontWeight.W400, //设置字体粗细
                                        fontSize = 15.sp,
                                        color = Color.White
                                    ),
                                )
                            }
                        }
                        //图片消息
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Image(
                                painter = painterResource(id = R.drawable.g4_6_img_imgmsg),
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth().height(300.dp)
                            )
                        }
                        //关闭按钮
                        Row(modifier = Modifier.fillMaxWidth().height(30.dp), horizontalArrangement = Arrangement.Center) {
                            Image(
                                painter = painterResource(id = R.drawable.g4_6_1_ic_cancel),
                                contentDescription = null,
                                modifier = Modifier.width(20.dp)
                            )
                        }
                    }


                }
            }




        }

    }

}