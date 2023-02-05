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
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.Gray1
import com.example.scaffolddemo.ui.theme.Green2


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MessageTapScreen(
//            bills : (String) -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},

    ) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "拍一拍记录",
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
                            contentDescription =""
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
                .padding(top = 5.dp, start = 8.dp, end = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(0.dp))
            TapMessageList(nav01, nav02,userViewModel)
        }
    }

}


//@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2, heightDp = 180)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TapMessageList(nav01: () -> Unit = {}, nav02: () -> Unit,    userViewModel: UserViewModel) {

    val listDat1 by remember {
        mutableStateOf(
            listOf(
                //如果需要改变下面对象里面的属性，需要单独复制一份生成一个新的对象才可以
                TapListItemModel(
                    "幻想世界",
                    "拍了拍你的向日葵",
                    R.drawable.g2_1_img_user01,
                    R.drawable.g2_1_btn_friend,
                    "1min前"
                ),
                TapListItemModel(
                    "sandr",
                    "拍了拍你的向日葵",
                    R.drawable.g2_1_img_user02,
                    R.drawable.g2_1_btn_friend_disabled,
                    "5min前"
                ),
                TapListItemModel(
                    "施&SHI",
                    "拍了拍你的向日葵",
                    R.drawable.g2_1_img_user03,
                    R.drawable.g2_1_btn_friend,
                    "5min前"
                ),
                TapListItemModel(
                    "ajunGrit",
                    "拍了拍你的向日葵",
                    R.drawable.g2_1_img_user04,
                    R.drawable.g2_1_btn_friend_disabled,
                    "12-01"
                ),
            )
        )
    }
    val listDat2 by remember {
        mutableStateOf(
            listOf(
                //如果需要改变下面对象里面的属性，需要单独复制一份生成一个新的对象才可以
                TapListItemModel(
                    "施&SHI",
                    "拍了拍你的向日葵",
                    R.drawable.g2_1_img_user03,
                    R.drawable.g2_1_btn_friend,
                    "11-29"
                ),
                TapListItemModel(
                    "ajunGrit",
                    "拍了拍你的向日葵",
                    R.drawable.g2_1_img_user04,
                    R.drawable.g2_1_btn_friend_disabled,
                    "11-26"
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
            TapMessageItem(
                name = listItemModel.name,
                msg = listItemModel.msg,
                res = listItemModel.res,
                res2 = listItemModel.res2,
                time = listItemModel.time,
                nav01,
                nav02,
                userViewModel
            )
        }
        Image(
            painter = painterResource(R.drawable.g2_2_img_time),
            contentDescription = "",
            modifier = Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally)

        )
        listDat2.forEachIndexed { index, listItemModel ->
            TapMessageItem(
                name = listItemModel.name,
                msg = listItemModel.msg,
                res = listItemModel.res,
                res2 = listItemModel.res2,
                time = listItemModel.time,
                nav01,
                nav02,
                userViewModel
            )
        }
//        Divider()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TapMessageItem(
    name: String,
    msg: String,
    res: Int,
    res2: Int,
    time: String,
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},
    userViewModel: UserViewModel
) {

    ListItem(
        modifier = Modifier
            .clickable(onClick = nav01)
            .background(
                color = Color.White
            ),
        colors = ListItemDefaults.colors(containerColor = Color.White),
        headlineText = {
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
                Spacer(Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = res2),
                    contentDescription = null,
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp).clickable(onClick = {userViewModel.uiState.value.openDialog.value=true})
                )
            }

        },
        supportingText = {
            Text(
                msg,
                fontSize = 13.sp,
                style = MaterialTheme.typography.bodySmall,
                color = Gray1,
                textAlign = TextAlign.Justify
            )
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
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp)
                    .clickable(onClick = nav02)
            )
        }
    )
}


data class TapListItemModel(
    val name: String,
    val msg: String,
    var res: Int,
    var res2: Int,
    var time: String
)