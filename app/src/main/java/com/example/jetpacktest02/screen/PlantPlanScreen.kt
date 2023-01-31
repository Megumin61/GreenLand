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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.*

/**
 * The Bills screen.
 */

@Composable
fun PlanItem(@DrawableRes iconRes:Int){
    Image(painter = painterResource(id = iconRes), contentDescription = null,
    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp))
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun NewScreen() {
    Image(
        painter = painterResource(id = R.drawable.g1_4_1_bg),
        contentDescription = null,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxSize()
        ,contentScale = ContentScale.FillHeight
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row() {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        bitmap = ImageBitmap.imageResource(id = R.drawable.g1_2_0_ic_arrow_left)
                        ,contentDescription =null, modifier = Modifier.offset(-140.dp,6.dp))
                }

                Text(
                    text = "每日计划",
                    style = TextStyle(
                        fontWeight = FontWeight.W900, //设置字体粗细
                        fontSize = 18.sp
                    ), modifier = Modifier.offset(-135.dp,18.dp)
                )

            }

        }
    }

        Image(painter = painterResource(id = R.drawable.g1_1_img_flower), contentDescription =null,
            modifier = Modifier
                .size(290.dp)
                .offset(50.dp, 130.dp))
    Box(modifier = Modifier.offset(0.dp,390.dp)) {
        Image(painter = painterResource(id = R.drawable.g1_4_1_bg_sports)
            , modifier = Modifier.padding(horizontal = 28.dp)
            , contentDescription =null)

        
    }
    
    



}




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
