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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.Green1
import com.example.scaffolddemo.ui.theme.Green2
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * 群岛/选择岛屿页面
 * 负责人：谭家俊
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IslandChooseIslandScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit = {},
) {
    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Green1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight)


    Surface(modifier = Modifier.fillMaxSize()) {
        //绘制背景渐变色
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
            //顶部菜单栏
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "岛屿选择",
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
                    Spacer(Modifier.height(40.dp))

                    Image(
                        painter = painterResource(id = R.drawable.g4_1_bn_friendisland),
                        contentDescription = null,
                        modifier = Modifier
                            .width(377.dp)
                            .height(230.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable(enabled = true, onClick = nav01),
                    )

                    Text(
                        text = "·关注朋友们的生活状态，增进互动",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xff9598ac)
                    )

                    Spacer(Modifier.height(25.dp))
                    Image(
                        painter = painterResource(id = R.drawable.g4_1_bn_exploreisland),
                        contentDescription = null,
                        modifier = Modifier
                            .width(377.dp)
                            .height(230.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable(
                                enabled = true, onClick = nav01,
                            ),

                        )
                    Text(
                        text = "·探索附近的岛友，多样互动，让生活更有乐趣",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xff9598ac)
                    )
                }
            }

        }
    }

//        Text("4.1-island-chooseIsland")
//        Button(
//            onClick = nav01,
//            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
//        ) {
//            Icon(
//                Icons.Filled.Favorite,
//                contentDescription = "Localized description",
//                modifier = Modifier.size(ButtonDefaults.IconSize)
//            )
//            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//            Text("1.1-Plant")
//        }


}

