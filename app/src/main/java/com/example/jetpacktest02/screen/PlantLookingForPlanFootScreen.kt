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

import androidx.annotation.DrawableRes
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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

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


@OptIn(ExperimentalPagerApi::class)
@Composable
fun PlantLookingForPlanFootScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit={},

) {
    /*Text("1.4.1-plant-lookingforplan-foot")
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
    }*/
    Column(Modifier.fillMaxSize(),verticalArrangement = Arrangement.Bottom) {
        Box(modifier = Modifier.fillMaxWidth(), Alignment.BottomCenter) {
            Image(
                painter = painterResource(id = R.drawable.plant_looking_for_plan_foot),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
            HorizontalPager(count = 2) { page ->
                CardPage(planname ="运动", aimcontent = "目标步数", realcontent ="实际步数", aimnum =3000, realnum =2786)
                CardPage(planname ="喝水", aimcontent = "目标步数", realcontent ="实际步数", aimnum =3000, realnum =2786)
            }


        }
    }
}





@Composable
fun DayCardItem(day:Int,bgcolor:Color){
    Card(modifier = Modifier.size(width = 32.dp, height = 40.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = bgcolor)) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = day.toString(), fontSize = 12.sp,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                textAlign = TextAlign.Justify, modifier = Modifier.padding(start = 2.dp, top = 11.dp))
        }
    }
}





        


