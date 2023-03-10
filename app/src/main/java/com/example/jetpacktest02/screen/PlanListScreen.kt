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
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.Green1
import com.example.scaffolddemo.ui.theme.Green2
import com.example.scaffolddemo.ui.theme.Green5
import com.example.scaffolddemo.ui.theme.Yellow1
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

/**
 * The Bills screen.
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCard() {
    var state1 by remember {
        mutableStateOf(false)
    }
    var state2 by remember {
        mutableStateOf(false)
    }
    var state3 by remember {
        mutableStateOf(false)
    }
    var state4 by remember {
        mutableStateOf(false)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun PlanListScreen(
    nav01: () -> Unit={},
    nav02: () -> Unit={},
    nav03: () -> Unit={},
    nav04: () -> Unit={},
    nav05: () -> Unit={},
    nav06: () -> Unit={}
){
    rememberSystemUiController().setStatusBarColor(
        Green1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )

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

                           /* IconButton(onClick = nav06,interactionSource = MutableInteractionSource()) {*/
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
                    /*var state by remember {
                        mutableStateOf(false)
                    }
                    LaunchedEffect(key1 = state) {
                        delay(100)
                        state = true
                    }*/
                    /*AnimatedVisibility(
                        visible = state,
                        enter = slideInVertically(initialOffsetY = { -40 }
                        ) +*//* expandVertically(
                            expandFrom = Alignment.Top
                        ) +*//* fadeIn(initialAlpha = 0.3f),
                        exit= fadeOut(targetAlpha = 0f) + shrinkVertically(shrinkTowards = Alignment.Top)
                    ){*/
                    var state1 by remember {
                        mutableStateOf(false)
                    }
                    var state2 by remember {
                        mutableStateOf(false)
                    }
                    var state3 by remember {
                        mutableStateOf(false)
                    }
                    var state4 by remember {
                        mutableStateOf(false)
                    }
                    LaunchedEffect(key1 = state1){
                        delay(100)
                        state1 = true
                    }
                    LaunchedEffect(key1 = state2){
                        delay(200)
                        state2 = true
                    }
                    LaunchedEffect(key1 = state3){
                        delay(300)
                        state3 = true
                    }
                    LaunchedEffect(key1 = state4){
                        delay(400)
                        state4 = true
                    }
                        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                            AnimatedVisibility(
                                visible = state1,
                                enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                                        fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
                                exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                                        fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                            ){
                            Image(
                                painter = painterResource(id = R.drawable.g1_2_icbg_sports),
                                contentDescription = null, modifier = Modifier.clickable(onClick = nav01
                                    , indication = null, interactionSource = MutableInteractionSource()
                                )

                            )}
                            AnimatedVisibility(
                                visible = state2,
                                enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                                        fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
                                exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                                        fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                            ){
                            Image(
                                painter = painterResource(id = R.drawable.g1_2_icbg_drinkwater),
                                contentDescription = null,modifier = Modifier.clickable(onClick = nav02
                                    , indication = null, interactionSource = MutableInteractionSource())
                            )}
                            AnimatedVisibility(
                                visible = state3,
                                enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                                        fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
                                exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                                        fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                            ){
                            Image(
                                painter = painterResource(id = R.drawable.g1_2_icbg_sleep),
                                contentDescription = null,modifier = Modifier.clickable(onClick = nav03
                                    , indication = null, interactionSource = MutableInteractionSource())
                            )}
                            AnimatedVisibility(
                                visible = state4,
                                enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                                        fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
                                exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                                        fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
                            ){
                            Image(
                                painter = painterResource(id = R.drawable.g1_2_icbg_eating),
                                contentDescription = null,modifier = Modifier.clickable(onClick = nav04
                                    , indication = null, interactionSource = MutableInteractionSource())
                            )}

                            Spacer(Modifier.height(5.dp))

                            /*Text(
                                text = "???????????????",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xff445B60)
                            )*/
                            Spacer(Modifier.height(5.dp))
                            Button(onClick =  nav05 ,interactionSource = MutableInteractionSource(), modifier = Modifier
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
    }






