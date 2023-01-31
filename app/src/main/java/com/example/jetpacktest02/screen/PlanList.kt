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
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.Green1
import com.example.scaffolddemo.ui.theme.Green2

/**
 * The Bills screen.
 */



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun PlanList(){
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
            androidx.compose.material.Scaffold(
                topBar = {
                    androidx.compose.material.TopAppBar(title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "每日计划",
                                style = TextStyle(
                                    fontWeight = FontWeight.W900, //设置字体粗细
                                    fontSize = 18.sp,
                                ),
                                modifier = Modifier.offset(-150.dp, 0.dp)//向左偏移一段距离
                            )
                        }
                    },
                        backgroundColor = Green1,
                        contentColor = Color.Black,
                        elevation = 0.dp, //设置阴影
                        //左侧按钮
                        navigationIcon = {

//                            IconButton(onClick = {}) {
//                                Icon(
//                                    Icons.Default.Menu,
//                                    contentDescription = "",
//                                )
//                            }
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
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "正在进行的计划",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xff445B60)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.g1_2_icbg_sports),
                        contentDescription = null
                    )
                    Image(
                        painter = painterResource(id = R.drawable.g1_2_icbg_drinkwater),
                        contentDescription = null
                    )
                    Image(
                        painter = painterResource(id = R.drawable.g1_2_icbg_sleep),
                        contentDescription = null
                    )
                    Image(
                        painter = painterResource(id = R.drawable.g1_2_icbg_eating),
                        contentDescription = null
                    )

                    Spacer(Modifier.height(15.dp))

                    Text(
                        text = "今天已完成",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xff445B60)
                    )
                    Button(onClick = { /*TODO*/ }, modifier = Modifier
                        .padding(top = 415.dp, start = 122.dp)
                        .width(136.dp)
                        .height(54.dp)
                        // .background(color = Color(0xff7FC7A8))


                    ) {
                        Text(text = "添加计划", fontSize = 20.sp, fontWeight = FontWeight.W900)


                    }
                }
            }

        }
    }



}




