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

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.*

/**
 * The Bills screen.
 */


@Composable
fun PlanCard(){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
//                    onClick = { /* Do something */ },
            modifier = Modifier.size(width = 380.dp, height = 350.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
            }
        }
        Spacer(modifier = Modifier.height(40.dp))

    }
}


@Composable
fun PlantLookingForPlanFootScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit={},

) {
    Column(Modifier.fillMaxSize(),verticalArrangement = Arrangement.Bottom) {

        Text("1.4.1-plant-lookingforplan-foot")
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
            Text("1.2.1-plant-foot")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally,
             modifier = Modifier.fillMaxWidth()) {
            Card(modifier = Modifier.size(width = 380.dp, height = 350.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)) {
            }

        }
        Image(
            painter = painterResource(id = R.drawable.plant_looking_for_plan_foot),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )

    }


}
@Preview
@Composable
fun CardTry(){


            Card(
                modifier = Modifier.size(width = 380.dp, height = 450.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Spacer(modifier = Modifier.padding(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "喝水", fontSize = 26.sp,
                            style = MaterialTheme.typography.labelLarge,
                            color = Green4,
                            textAlign = TextAlign.Justify)

                        Text(text = "每日打卡", fontSize = 12.sp,
                            style = MaterialTheme.typography.bodySmall,
                            color = Gray1,
                            textAlign = TextAlign.Justify)
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)) {
                        Text(text = "创建时间："+"当日日期", fontSize = 12.sp,
                            style = MaterialTheme.typography.bodySmall,
                            color = Gray1,
                            textAlign = TextAlign.Justify)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "已完成打卡"+"1/5", fontSize = 12.sp,
                            style = MaterialTheme.typography.bodySmall,
                            color = Gray1,
                            textAlign = TextAlign.Justify)

                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)){

                        Text(text = "查看全部日历", fontSize = 12.sp,
                            style = MaterialTheme.typography.bodySmall,
                            color = Gray1,
                            textAlign = TextAlign.Justify)
                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                    androidx.compose.material.Divider(
                        color = Color.LightGray,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)){
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 30.dp)) {
                            Text(text = "目标次数", fontSize = 12.sp,
                                style = MaterialTheme.typography.bodySmall,
                                color = Gray1,
                                textAlign = TextAlign.Justify, modifier = Modifier.padding(bottom = 5.dp))

                            Text(text = "25", fontSize = 26.sp,
                                style = MaterialTheme.typography.labelLarge,
                                color = Green5,
                                textAlign = TextAlign.Justify)
                        }
                        Column( horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.padding(end = 30.dp)) {
                            Text(text = "实际次数", fontSize = 12.sp,
                                style = MaterialTheme.typography.bodySmall,
                                color = Gray1,
                                textAlign = TextAlign.Justify, modifier = Modifier.padding(bottom = 5.dp))
                            Text(text = "18", fontSize = 26.sp,
                                style = MaterialTheme.typography.labelLarge,
                                color = Green5,
                                textAlign = TextAlign.Justify)
                        }
                    }
                    Spacer(modifier = Modifier.padding(15.dp))
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "百分数", fontSize = 26.sp,
                            style = MaterialTheme.typography.labelLarge,
                            color = Green5,
                            textAlign = TextAlign.Justify)
                    }
                }
            }
            
        }
        


