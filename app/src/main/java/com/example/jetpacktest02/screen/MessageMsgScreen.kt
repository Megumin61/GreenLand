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
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.navigation.NavHostController
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.material.color.MaterialColors


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MessageMsgScreen(
//            bills : (String) -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    nav01: () -> Unit = {},
    controller: NavHostController

    ) {
    //???????????????????????????
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
                            text = "???????????????",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //??????????????????
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-35.dp, 0.dp)//????????????????????????
                        )
                    }
                },
                //????????????
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
                elevation = 0.dp, //????????????
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
            MsgMessageList(nav01, userViewModel,controller)
        }
    }

}


//@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2, heightDp = 180)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MsgMessageList(nav01: () -> Unit = {},  userViewModel: UserViewModel,controller:NavHostController) {

    val listDat1 by remember {
        mutableStateOf(
            listOf(
                //?????????????????????????????????????????????????????????????????????????????????????????????????????????
                MsgListItemModel(
                    "Sandor",
                    "Please vivo 50",
                    R.drawable.userprofile_15,
                    R.drawable.g2_1_btn_friend_disabled,
                    "5min???"
                ),
            )
        )
    }
    val listDat2 by remember {
        mutableStateOf(
            listOf(
                //?????????????????????????????????????????????????????????????????????????????????????????????????????????
                MsgListItemModel(
                    "???&SHI",
                    "????????????????????????????????????????????????",
                    R.drawable.userprofile_16,
                    R.drawable.g2_1_btn_friend,
                    "11-29"
                ),
                MsgListItemModel(
                    "ajunGrit",
                    "????????????????????????????????????????????????????????????????????????????????????",
                    R.drawable.userprofile_17,
                    R.drawable.g2_1_btn_friend,
                    "11-26"
                ),
                MsgListItemModel(
                    "foxBread",
                    "?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????",
                    R.drawable.userprofile_18,
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
            MsgMessageItem(
                name = listItemModel.name,
                msg = listItemModel.msg,
                res = listItemModel.res,
                res2 = listItemModel.res2,
                time = listItemModel.time,
                nav01,
                userViewModel,
                controller
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
            MsgMessageItem(
                name = listItemModel.name,
                msg = listItemModel.msg,
                res = listItemModel.res,
                res2 = listItemModel.res2,
                time = listItemModel.time,
                nav01,
                userViewModel,
                controller
            )
        }
//        Divider()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MsgMessageItem(
    name: String,
    msg: String,
    res: Int,
    res2: Int,
    time: String,
    nav01: () -> Unit = {},
    userViewModel: UserViewModel,
    controller: NavHostController
) {
    ListItem(
        modifier = Modifier
//            .clickable(onClick = nav01)
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
                            },                                    indication = null,
                                interactionSource = MutableInteractionSource()
                            )
                    )
                }
                Text(
                    "???????????????",
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

            Card(
                colors = CardDefaults.cardColors(containerColor = BlueGray1),
//                modifier = Modifier.padding(start = 6.dp, end = 6.dp, top = 2.dp, bottom = 2.dp)
            ) {
                Text(
                    msg,
                    textAlign = TextAlign.Left,
                    lineHeight = 20.sp,
                    color= Color.Black,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium, //??????????????????
                        fontSize = 13.sp,
                    ),
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom =10.dp)
                )
            }
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
                        controller.navigate("4.5-island-visitOther/$res/$name")//?????????id?????????????????????
                    },                                    indication = null,
                        interactionSource = MutableInteractionSource())
                    .offset(0.dp,-25.dp)
            )
        }
    )

}


data class MsgListItemModel(
    val name: String,//?????????
    val msg: String,//???????????????
    var res: Int,//??????
    var res2: Int,//??????????????????
    var time: String//????????????
)
