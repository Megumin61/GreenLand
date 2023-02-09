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
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MessagePicScreen(
//            bills : (String) -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    nav01: () -> Unit = {},//返回上一级
    nav02: () -> Unit = {},//去到空间
    controller: NavHostController
    ) {
    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Color.White, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "收到的图片",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //设置字体粗细
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-35.dp, 0.dp)//向左偏移一段距离
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
                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 0.dp, //设置阴影
            )
        }
    ) {
        DialogCard(userViewModel)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 0.dp, end = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(0.dp))
            PicMessageList(nav01, nav02, userViewModel,controller=controller)
        }
    }

}


//@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2, heightDp = 180)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PicMessageList(nav01: () -> Unit = {}, nav02: () -> Unit, userViewModel: UserViewModel,controller:NavHostController) {

    val listDat1 by remember {
        mutableStateOf(
            listOf(
                //如果需要改变下面对象里面的属性，需要单独复制一份生成一个新的对象才可以
                PicListItemModel(
                    "Sandor",
                    R.drawable.userprofile_21,
                    R.drawable.g2_1_btn_friend_disabled,
                    R.drawable.g2_4_img_pic,
                    "5min前"
                ),
                PicListItemModel(
                    "留猪侨",
                    R.drawable.userprofile_23,
                    R.drawable.g2_1_btn_friend_disabled,
                    R.drawable.g2_4_img_pic,
                    "5min前"
                ),
            )
        )
    }

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        listDat1.forEachIndexed { index, listItemModel ->
            MsgPicItem(
                name = listItemModel.name,
                res = listItemModel.res,
                res2 = listItemModel.res2,
                res3 = listItemModel.res3,
                time = listItemModel.time,
                nav01,
                userViewModel,
                controller = controller
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MsgPicItem(
    name: String,
    res: Int,
    res2: Int,
    res3: Int,
    time: String,
    nav01: () -> Unit = {},
    userViewModel: UserViewModel,
    controller:NavHostController
) {
    val enable = remember {
        mutableStateOf(true)
    }
    val size =
        animateSizeAsState(targetValue = if (enable.value) Size(120f, 120f) else Size(250f, 250f))
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        ListItem(
            modifier = Modifier
                .clickable(onClick = nav01)
                .background(
                    color = Color.White
                ),
            colors = ListItemDefaults.colors(containerColor = Color.White),
            headlineText = {
                Column() {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = name,
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500,
                        )
                        Spacer(Modifier.width(12.dp))
                        Image(
                            painter = painterResource(id = res2),
                            contentDescription = null,
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .clickable(onClick = {
                                    userViewModel.uiState.value.openDialog.value = true
                                })
                        )
                    }
                    Text(
                        "向你投放了图片",
                        fontSize = 13.sp,
                        style = MaterialTheme.typography.bodySmall,
                        color = Gray1,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.offset(0.dp, (-5).dp)
                    )
                }

            },
            supportingText = {
                Spacer(modifier = Modifier.height(4.dp))
            },
            trailingContent = {
                Text(
                    time,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray1,
                    textAlign = TextAlign.Center
                )
            },

            leadingContent = {
                    Image(
                        painter = painterResource(id = res),
                        contentDescription = null,
                        alignment = Alignment.TopCenter,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .clickable(onClick = {
                                controller.navigate("4.5-island-visitOther/$res/$name")//这里将id拼接到参数后面
                            }
//                            .offset(0.dp, -25.dp)
                    ))
                }
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = BlueGray1),
            modifier = Modifier.offset(80.dp,0.dp)
//                modifier = Modifier.padding(start = 6.dp, end = 6.dp, top = 2.dp, bottom = 2.dp)
        ) {
            Image(
                painter = painterResource(id = res3),
                contentDescription = null,
                modifier = Modifier
                    .size(size.value.width.dp, size.value.height.dp)
                    .clickable(onClick = {
                        enable.value = !enable.value
                    })
            )
        }
    }

}


data class PicListItemModel(
    val name: String,//用户名
    var res: Int,//头像
    var res2: Int,//添加好友图片，
    var res3: Int,//投放的图片
    var time: String//当前时间
)