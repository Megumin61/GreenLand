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

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jetpacktest02.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MessageScreen(
//            bills : (String) -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    nav01: () -> Unit = {},//?????????
    nav02: () -> Unit = {},//????????????
    nav03: () -> Unit = {},//????????????
    nav04: () -> Unit = {},//????????????
    nav05: () -> Unit = {},//????????????
    controller: NavHostController
) {
    //???????????????????????????
    rememberSystemUiController().setStatusBarColor(
        White, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
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
                            text = "??????",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //??????????????????
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-15.dp, 0.dp)//????????????????????????
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
            Spacer(Modifier.height(10.dp))
            IconButtonList(nav01, nav03, nav04, nav05)
            Spacer(Modifier.height(20.dp))
            MessageList(
                nav01,
                nav02,
                nav03,
                nav04,
                nav05,
                userViewModel = userViewModel,
                controller = controller
            )
        }

//        Column {
//
//            Text("2.1-message")
//            Button(
//                onClick = nav01,
//                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
//            ) {
//                Icon(
//                    Icons.Filled.Favorite,
//                    contentDescription = "Localized description",
//                    modifier = Modifier.size(ButtonDefaults.IconSize)
//                )
//                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//                Text("1.1-Plant")
//            }
//        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconButtonList(
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},
    nav03: () -> Unit = {},
    nav04: () -> Unit = {}
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,//????????????????????????????????????

    ) {
        Spacer(Modifier.height(50.dp))
        Image(
            painter = painterResource(id = R.drawable.g2_1_ic_tap),
            contentDescription = null,
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .clickable(
                    onClick = nav01,
                    indication = null,
                    interactionSource = MutableInteractionSource()
                )

        )
        Image(
            painter = painterResource(id = R.drawable.g2_1_ic_message),
            contentDescription = null,
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .clickable(
                    onClick = nav02, indication = null,
                    interactionSource = MutableInteractionSource()
                )

        )
        Image(
            painter = painterResource(id = R.drawable.g2_1_ic_picture),
            contentDescription = null,
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .clickable(
                    onClick = nav03, indication = null,
                    interactionSource = MutableInteractionSource()
                )

        )
        Image(
            painter = painterResource(id = R.drawable.g2_1_ic_friend),
            contentDescription = null,
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .clickable(
                    onClick = nav04, indication = null,
                    interactionSource = MutableInteractionSource()
                )
        )

    }
}

//@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2, heightDp = 180)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageList(
    nav01: () -> Unit = {},//?????????
    nav02: () -> Unit,//????????????
    nav03: () -> Unit = {},//????????????
    nav04: () -> Unit = {},//????????????
    nav05: () -> Unit = {},//????????????
    controller: NavHostController,
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {

    val listData by remember {
        mutableStateOf(
            listOf(
                //?????????????????????????????????????????????????????????????????????????????????????????????????????????
                ListItemModel(
                    "????????????",
                    "????????????????????????",
                    R.drawable.userprofile_7,
                    R.drawable.g2_1_btn_friend,
                    "5min???",
                    nav01
                ),
                ListItemModel(
                    "sandr",
                    "?????????????????????",
                    R.drawable.userprofile_10,
                    R.drawable.g2_1_btn_friend_disabled,
                    "8min???",
                    nav04
                ),
                ListItemModel(
                    "???&SHI",
                    "????????????",
                    R.drawable.userprofile_9,
                    R.drawable.g2_1_btn_friend,
                    "14min???",
                    nav03
                ),
                ListItemModel(
                    "ajunGrit",
                    "????????????????????????",
                    R.drawable.userprofile_11,
                    R.drawable.g2_1_btn_friend_disabled,
                    "17min???",
                    nav01
                ),
                ListItemModel(
                    "foxbread",
                    "????????????????????????",
                    R.drawable.userprofile_12,
                    R.drawable.g2_1_btn_friend,
                    "45min???",
                    nav01
                ),
                ListItemModel(
                    "????????????",
                    "????????????????????????",
                    R.drawable.userprofile_13,
                    R.drawable.g2_1_btn_friend_disabled,
                    "1h???",
                    nav01
                ),
                ListItemModel(
                    "sandr",
                    "?????????????????????",
                    R.drawable.userprofile_14,
                    R.drawable.g2_1_btn_friend,
                    "1h???",
                    nav04
                ),
                ListItemModel(
                    "???&SHI",
                    "????????????",
                    R.drawable.userprofile_15,
                    R.drawable.g2_1_btn_friend_disabled,
                    "11-31",
                    nav03
                ),
                ListItemModel(
                    "ajunGrit",
                    "????????????????????????",
                    R.drawable.userprofile_16,
                    R.drawable.g2_1_btn_friend,
                    "11-31",
                    nav01
                ),
                ListItemModel(
                    "foxbread",
                    "????????????????????????",
                    R.drawable.userprofile_17,
                    R.drawable.g2_1_btn_friend_disabled,
                    "11-31",
                    nav01
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
        listData.forEachIndexed { index, listItemModel ->
            MessageItem(
                name = listItemModel.name,
                msg = listItemModel.msg,
                res = listItemModel.res,
                res2 = listItemModel.res2,
                time = listItemModel.time,
                nav01 = listItemModel.nav,
                controller = controller,
                userViewModel
            )
        }
//        Divider()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageItem(
    name: String,
    msg: String,
    res: Int,
    res2: Int,
    time: String,
    nav01: () -> Unit = {},//?????????????????????
    controller: NavHostController,
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
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
                        .height(40.dp)
                        .clickable(
                            onClick = {
                                userViewModel._uiState.value.openDialog.value = true
                            }, indication = null,
                            interactionSource = MutableInteractionSource()
                        )
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
                    .clickable(
                        onClick = {
                            controller.navigate("4.5-island-visitOther/$res/$name")//?????????id?????????????????????
                        }, indication = null,
                        interactionSource = MutableInteractionSource()
                    ),
            )
        }
    )
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DialogCard(userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }

    if (userViewModel.uiState.value.openDialog.value) {
        Dialog(
            onDismissRequest = { userViewModel.uiState.value.openDialog.value = false },
        ) {
            Column() {
                Card(
//                    onClick = { /* Do something */ },
                    modifier = Modifier.size(width = 380.dp, height = 350.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = White)
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "????????????",
                            style = TextStyle(
                                fontWeight = FontWeight.ExtraBold, //??????????????????
                                fontSize = 22.sp,
                            ),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Image(
                            painter = painterResource(id = R.drawable.g2_1_img_user03),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .scale(1.2f)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "???&SHI",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        TextField(
                            value = text,
                            shape = RoundedCornerShape(10.dp),
                            onValueChange = { text = it },
                            singleLine = false,
                            placeholder = { Text("?????????????????????????????????", fontSize = 14.sp, color = Gray2) },
//                            label={ Text("?????????????????????????????????", fontSize = 14.sp, color = Gray2) },
                            modifier = Modifier
                                .size(250.dp, 60.dp)
                                .align(Alignment.CenterHorizontally),
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Gray2,
                                containerColor = Gray3,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        Image(
                            painter = painterResource(id = R.drawable.g4_4_btn_addfriend),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .clickable(
                                    onClick = {
                                        userViewModel.uiState.value.openDialog.value = false
                                    }, indication = null,
                                    interactionSource = MutableInteractionSource()
                                ),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Image(
                    painter = painterResource(id = R.drawable.g4_4_btn_close),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable(onClick = {
                            userViewModel.uiState.value.openDialog.value = false
                        })
                )
            }
        }
    }
}

data class ListItemModel(
    val name: String,
    val msg: String,
    var res: Int,
    var res2: Int,
    var time: String,
    val nav: () -> Unit//??????????????????
)