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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.TapListItemModel
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.compose.StepCounter
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * The Bills screen.
 */

//viewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
@SuppressLint("StateFlowValueCalledInComposition")
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
    userViewModel: UserViewModel

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
        Text("1.1-Plant")
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "应用运行以来的步数：${userViewModel.uiState.value.stepDetector.value}",
                style = TextStyle(fontSize = 20.sp)
            )
            Text(
                text = "开机以来的步数：${userViewModel.uiState.value.stepCounter.value}",
                style = TextStyle(fontSize = 20.sp)
            )
        }
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
    MainPlantPage(userViewModel, nav01)

}

//@Preview
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainPlantPage(
    userViewModel: UserViewModel,
    nav01: () -> Unit = {},//计划
) {
    val feelingValue: Int = 0

    val energyValue: Int = 0
    val allEnergyValue: Int = 0

    Column() {
        Image(
            painter = painterResource(id = R.drawable.g1_1_ic_ar),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.End)
                .offset(-20.dp, 0.dp)
                .width(100.dp)
                .height(70.dp)
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
                modifier = Modifier.offset(5.dp, 30.dp)
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
                        .height(45.dp)
                        .offset(0.dp, -70.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(
                modifier = Modifier
                    .width(120.dp)
                    .padding(10.dp),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(100.dp),
                    modifier = Modifier
                        .height(180.dp)
                        .width(80.dp)
                        .offset(10.dp, 0.dp)

                ) {
                    Column(
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.g1_1_ic_plan),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                                .clickable(onClick = nav01)
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
                Box(modifier = Modifier.align(Alignment.Start)) {
                    Image(
                        painter = painterResource(id = R.drawable.g1_1_ic_energy),
                        contentDescription = null,
                        modifier = Modifier.scale(1.2f)
                    )
                    Text(
                        text = allEnergyValue.toString(),
                        fontWeight = W500,
                        color = Green9,
                        modifier = Modifier.offset(72.dp, 12.dp)
                    )
                }
            }
        }
        //心情、水分、能量
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.offset(0.dp, -20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxHeight()
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.align(Alignment.Start)) {
                    Image(
                        painter = painterResource(id = R.drawable.g1_1_ic_feeling),
                        contentDescription = null,
                        modifier = Modifier
                            .width(140.dp)
                    )
                    Text(
                        text = feelingValue.toString() + "g",
                        fontWeight = W900,
                        color = BlueGray3,
                        modifier = Modifier.offset(80.dp, 30.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.align(Alignment.Start)) {
                    Image(
                        painter = painterResource(id = R.drawable.g1_1_ic_water),
                        contentDescription = null,
                        modifier = Modifier
                            .width(140.dp)
                    )
                    Text(
                        text = userViewModel.uiState.value.waterValue.toString() + "g",
                        fontWeight = W900,
                        color = BlueGray3,
                        modifier = Modifier.offset(80.dp, 30.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.align(Alignment.Start)) {
                    Image(
                        painter = painterResource(id = R.drawable.g1_1_ic_huoli),
                        contentDescription = null,
                        modifier = Modifier
                            .width(140.dp)
                    )
                    Text(
                        text = energyValue.toString() + "g",
                        fontWeight = W900,
                        color = BlueGray3,
                        modifier = Modifier.offset(80.dp, 30.dp)
                    )
                }
            }
            //消息
            Column(
                horizontalAlignment = Alignment.End, modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.g1_1_ic_message),
                    contentDescription = null,

                    )
                Box() {
                    Image(
                        painter = painterResource(id = R.drawable.g1_1icbg_message),
                        contentDescription = null,
                    )
                    LazyColumn(
                        modifier = Modifier
                            .height(160.dp)
                            .padding(8.dp)
                    ) {
//                        userViewModel.uiState.value.tabMessageList.forEach {listItemModel->
//                            PlantMsgItem(listItemModel)
//
//                        }
                        items(userViewModel.uiState.value.tabMessageList) { item ->
                            PlantMsgItem(item)
                            Spacer(modifier = Modifier.height(2.dp))
                        }
                    }
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.g1_1_ic_arrow_down),
            contentDescription = null,
            modifier = Modifier
                .align(CenterHorizontally)
                .offset(0.dp, (-50).dp)
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantMsgItem(listItemModel: TapListItemModel) {
    Row(Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = listItemModel.res),
            contentDescription = null,
            modifier = Modifier
                .width(28.dp)
                .height(28.dp)
                .border(width = 1.dp, color = LightGreen, shape = RoundedCornerShape(15.dp))
//                    .clickable(onClick = nav02)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Column() {
            Text(
                text = listItemModel.name,
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
            )
            Text(
                listItemModel.msg,
                fontSize = 10.sp,
                style = MaterialTheme.typography.bodySmall,
                color = Gray1,
                textAlign = TextAlign.Justify
            )
        }
    }


}
