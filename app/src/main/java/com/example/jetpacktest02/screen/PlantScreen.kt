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

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * The Bills screen.
 */

//viewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
@Composable
fun PlantScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},
    nav03: () -> Unit = {},
    nav04: () -> Unit = {},
    nav05: () -> Unit = {},
    nav06: () -> Unit = {},
    nav07: () -> Unit = {},

    ) {
    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Color.White, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )
    Image(
        painter = painterResource(id = R.drawable.g1_1_bg_plant),
        contentDescription = null,
        modifier = Modifier
            .scale(1.1f, 1.0f)
            .fillMaxHeight()
            .fillMaxSize()
    )
    Column {
        Button(
            onClick = {
            },
        ) {
            BasicText(text = "Add 1")
        }

        Text("1.1-Plant")
        Button(
            onClick = nav01,
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("1.2-plant-plan")
        }
        Button(
            onClick = nav02,
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("1.3-Dailyhealthmessage")
        }
        Button(
            onClick = nav03,
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("4.1-island-chooseIsland")
        }
        Button(
            onClick = nav04,
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("2.1-message")
        }
        Button(
            onClick = nav05,
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("7.0-My")
        }
        Button(
            onClick = nav06,
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("5.1.1-PlantBag-possessed")
        }
        Button(
            onClick = nav07,
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("test")
        }
    }
    MainPlantPage()

}

@Preview
@Composable
fun MainPlantPage() {
    Column() {
        Image(
            painter = painterResource(id = R.drawable.g1_1_ic_ar),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.End)
                .offset(-20.dp,0.dp)
                .width(100.dp).height(70.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.offset(5.dp,30.dp)
            ) {
                Text(
                    "Joyce的向日葵",
                    fontSize = 25.sp,
                    fontWeight = W500,
                )
                Image(
                    painter = painterResource(id = R.drawable.g1_1_img_plant_experience),
                    contentDescription = null,
                )
                Image(
                    painter = painterResource(id = R.drawable.g1_1_img_flower),
                    contentDescription = null,
                    modifier = Modifier
                        .width(180.dp)
                        .height(300.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.g1_1_bg_plantstage),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .offset(0.dp, -80.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(
                modifier = Modifier.width(120.dp).padding(10.dp),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(100.dp),
                    modifier = Modifier
                        .height(180.dp)
                        .width(80.dp).offset(10.dp,0.dp)

                    ) {
                    Column(horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.g1_1_ic_plan),
                            contentDescription = null,
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Image(
                            painter = painterResource(id = R.drawable.g1_1_ic_bag),
                            contentDescription = null,
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(60.dp))
                Image(
                    painter = painterResource(id = R.drawable.g1_1_ic_energy),
                    contentDescription = null,
                    modifier = Modifier.scale(1.2f)
                )
            }
        }
        //心情、水分、能量
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.offset(0.dp,-20.dp)) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.g1_1_ic_feeling),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .width(140.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.g1_1_ic_water),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .width(140.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.g1_1_ic_huoli),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .width(140.dp)
                )
            }
            Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth().padding(20.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.g1_1_ic_message),
                    contentDescription = null,

                    )
                Image(
                    painter = painterResource(id = R.drawable.g1_1icbg_message),
                    contentDescription = null,
                )
            }

        }
        Image(
            painter = painterResource(id = R.drawable.g1_1_ic_arrow_down),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}