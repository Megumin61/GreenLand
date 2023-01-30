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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R

/**
 * The Bills screen.
 */

@Composable
fun PlanItem(@DrawableRes iconRes:Int){
    Image(painter = painterResource(id = iconRes), contentDescription = null,
    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp))


}

@Preview
@Composable
fun PlantPlanScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit={},
    nav02: () -> Unit={},

) {
    Image(
        painter = painterResource(id = R.drawable.plant_daily_plan),
        contentDescription = null,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxSize()
    )
    Column{
       /* Text("1.2-plant-plan")
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
            Text("1.1-Plant")
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
            Text("1.4.1-plant-lookingforplan-foot")
        }*/
        Row(Modifier.padding(top = 62.dp, start = 26.dp), horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable.g1_2_0_ic_arrow_left),
                contentDescription =null)
            Text(text = "每日计划", color = Color.Black, fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 18.dp)
                    .offset(y = -4.dp) )
        }
        Text(text = "正在进行的计划",color= Color.Gray, fontSize = 16.sp,
        modifier = Modifier.padding(top = 16.dp, start = 136.dp))
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            PlanItem(iconRes = R.drawable.g1_2_icbg_sports)
            PlanItem(iconRes = R.drawable.g1_2_icbg_drinkwater)
            PlanItem(iconRes = R.drawable.g1_2_icbg_sleep)
            PlanItem(iconRes = R.drawable.g1_2_icbg_eating)
        }
        Text(text = "今日已完成",color= Color.Gray, fontSize = 16.sp)
        Image(painter = painterResource(id = R.drawable.g1_2_4_btn_addplan), contentDescription =null )
        /*Button(
            onClick = nav01,
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("1.2-plant-plan")*/

    }


}
