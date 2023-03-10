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
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.screen.DiyPlanName
import com.example.scaffolddemo.ui.theme.Green1
import com.example.scaffolddemo.ui.theme.Green2
import com.example.scaffolddemo.ui.theme.Green5
import kotlinx.coroutines.delay

/**
 * The Bills screen.
 */



@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")

@Composable
fun PlanListAddedScreen(
    nav:()->Unit={},
    nav01: () -> Unit={},
    nav02: () -> Unit={},
    nav03: () -> Unit={},
    nav04: () -> Unit={},
    nav05: () -> Unit={},
    nav06: () -> Unit={},
    nav07: () -> Unit={},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
){
    var state by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = state) {
        delay(300)
        state = true
    }


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
                                text = "????????????",
                                style = TextStyle(
                                    fontWeight = FontWeight.W900, //??????????????????
                                    fontSize = 18.sp,
                                ),
                                modifier = Modifier.offset(-150.dp, 0.dp)//????????????????????????
                            )
                        }
                    },
                        backgroundColor = Green1,
                        contentColor = Color.Black,
                        elevation = 0.dp, //????????????
                        //????????????
                        navigationIcon = {
                            Icon(
                                bitmap = ImageBitmap.imageResource(id = R.drawable.g1_2_0_ic_arrow_left),
                                contentDescription = null, modifier = Modifier.offset(10.dp).clickable(onClick =nav06, indication = null, interactionSource = MutableInteractionSource() )
                            )
                        },
                        //????????????
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
                        .verticalScroll(scrollState)
                ) {
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "?????????????????????",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xff445B60)
                    )
                    AnimatedVisibility(
                        visible = state,
                        enter =slideInVertically(initialOffsetY = { -40 }
                                    ) + expandVertically(
                        expandFrom = Alignment.Top
                    ) + fadeIn(initialAlpha = 0.3f),
                    exit= fadeOut(targetAlpha = 0f) + shrinkVertically(shrinkTowards = Alignment.Top)
                    ) {
                        Box(Modifier.fillMaxWidth()) {

                            Image(
                                painter = painterResource(id = R.drawable.g1_2_icbg_newdiy),
                                contentDescription = null, modifier = Modifier.fillMaxWidth()
                            )
                            var PlanName by rememberSaveable { mutableStateOf("") }
                            DiyPlanName(PlanName = userViewModel.uiState.value.diyPlanName.value, onNumChange = { PlanName = it })
                        }
                    }
                        Image(
                            painter = painterResource(id = R.drawable.g1_2_icbg_sports),
                            contentDescription = null, modifier = Modifier.clickable(onClick = nav01
                                , indication = null, interactionSource = MutableInteractionSource()
                            )
                        )
                        Image(
                            painter = painterResource(id = R.drawable.g1_2_icbg_drinkwater),
                            contentDescription = null,modifier = Modifier.clickable(onClick = nav02
                                , indication = null, interactionSource = MutableInteractionSource())
                        )
                        Image(
                            painter = painterResource(id = R.drawable.g1_2_icbg_sleep),
                            contentDescription = null,modifier = Modifier.clickable(onClick = nav03
                                , indication = null, interactionSource = MutableInteractionSource())
                        )
                        Image(
                            painter = painterResource(id = R.drawable.g1_2_icbg_eating),
                            contentDescription = null,modifier = Modifier.clickable(onClick = nav04
                                , indication = null, interactionSource = MutableInteractionSource())
                        )

                        Spacer(Modifier.height(5.dp))

                        Spacer(Modifier.height(5.dp))
                        Button(onClick =nav07 ,interactionSource = MutableInteractionSource(), modifier = Modifier
                            .width(136.dp)
                            .height(54.dp)
                            ,colors = ButtonDefaults.buttonColors(containerColor = Green5)
                        ) {
                            Text(text = "????????????", fontSize = 20.sp, fontWeight = FontWeight.W900)
                        }
                    }
                }
            }


        }
    }





