package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IslandDeliverScreen(
    nav01: () -> Unit = {},
) {
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
                            text = "",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //设置字体粗细
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-35.dp, 0.dp)//向左偏移一段距离
                        )
                    }
                },
                    backgroundColor = Flesh1,
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
                    actions = { }

                )
            }
        ) {

            //页面背景颜色
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
                //页面内容的最外层Col
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 55.dp, vertical = 40.dp)
                ) {
                    //切换按钮
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.g4_6_ic_toggletophoto),
                            contentDescription = null,
                            modifier = Modifier
                                .width(130.dp)
                                .offset(y = 18.dp),
                        )
                    }

                    //文字输入卡片
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp), horizontalArrangement = Arrangement.End
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp)
                                .padding(13.dp) // 外边距
                            ,
                            backgroundColor = Color.White,
                            shape = MaterialTheme.shapes.large,
                            elevation = 1.5.dp

                        ) {
                            //上方区域
                            Column(
                                modifier = Modifier.padding(
                                    start = 14.dp,
                                    end = 14.dp,
                                    top = 20.dp,
                                    bottom = 35.dp
                                )
                            ) {
                                //文字输入区
                                Row(modifier = Modifier) {

                                    var text by remember { mutableStateOf("大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！") }

                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.White),
                                        contentAlignment = Alignment.TopStart
                                    ) {
                                        BasicTextField(
                                            value = text,
                                            onValueChange = {
                                                text = it
                                            },
                                            modifier = Modifier
                                                .background(Color.White, CircleShape)

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
                                    .padding(14.dp),
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                //字数标注标签
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(
                                        buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    fontWeight = FontWeight.W600,
                                                    fontSize = 12.sp,
                                                    color = Color(0xFF4552B8)
                                                )
                                            ) {
                                                append("20")
                                            }
                                            append("/100")
                                        },
                                        fontSize = 12.sp,
                                        color = Gray1
                                    )
                                }
                            }


                        }
                    }


                }

            }
        }

    }
}


@Preview("Light Mode")
@Composable
fun DefaultPreview() {

    Column(
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
        //页面内容的最外层Col
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 55.dp, vertical = 40.dp)
        ) {
            //切换按钮
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Image(
                    painter = painterResource(id = R.drawable.g4_6_ic_toggletophoto),
                    contentDescription = null,
                    modifier = Modifier
                        .width(130.dp)
                        .offset(y = 18.dp),
                )
            }

            //文字输入卡片
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp), horizontalArrangement = Arrangement.End
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .padding(13.dp) // 外边距
                    ,
                    backgroundColor = Color.White,
                    shape = MaterialTheme.shapes.large,
                    elevation = 1.5.dp

                ) {
                    //上方区域
                    Column(
                        modifier = Modifier.padding(
                            start = 14.dp,
                            end = 14.dp,
                            top = 20.dp,
                            bottom = 35.dp
                        )
                    ) {
                        //文字输入区
                        Row(modifier = Modifier) {

                            var text by remember { mutableStateOf("大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！大家新年快乐鸭！！！开工大吉！！") }

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White),
                                contentAlignment = Alignment.TopStart
                            ) {
                                BasicTextField(
                                    value = text,
                                    onValueChange = {
                                        text = it
                                    },
                                    modifier = Modifier
                                        .background(Color.White, CircleShape)

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
                            .padding(14.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        //字数标注标签
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.W600,
                                            fontSize = 12.sp,
                                            color = Color(0xFF4552B8)
                                        )
                                    ) {
                                        append("20")
                                    }
                                    append("/100")
                                },
                                fontSize = 12.sp,
                                color = Gray1
                            )
                        }
                    }


                }
            }


        }

    }
}