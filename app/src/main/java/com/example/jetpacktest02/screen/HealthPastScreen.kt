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

import android.graphics.Point
import android.util.Log
import android.widget.ProgressBar
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.composed
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.InspectorValueInfo
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.jetpacktest02.compose.MyTopAppBar
import com.example.scaffolddemo.ui.theme.Gray1
import com.example.scaffolddemo.ui.theme.Green1
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

/**
 * The Bills screen.
 */
@Preview(showBackground=true,widthDp=393,heightDp=2000)
@Composable
fun HealthPastScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit={},

) {
    Column {
        HealthTopAppBar()
        HealthViewTabRow()
        Box(
            modifier = Modifier
                .size(393.dp, 2000.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(246, 235, 222),
                            Color(195, 216, 190)
                        )
                    )
                )
        )
        {
            /*Column(modifier = Modifier
                .padding(top = 20.dp, start = 26.dp, end = 26.dp)
                , verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                StepFrame()
                SitFrame()
                WaterFrame()

            }*/


            LazyColumn(
                Modifier
                    .fillMaxWidth() // 宽度填满父空间
                    .height(2000.dp)
                    .padding(top = 20.dp, start = 26.dp, end = 26.dp),


                verticalArrangement = Arrangement.spacedBy(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                content = {
                    item{
                    Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(24.dp)){
                        ImgPlant()
                        InformationRow()
                    }


                    }
                    item{
                        StepFrame()
                    }
                    item{
                        SitFrame()
                    }
                    item{
                        WaterFrame()
                    }
                    item{
                        ImgAdviceFrame()
                    }
                    item{
                        BtnGetConclusion()
                    }


                })
        }
    }




    /*Column{
        Text("1.2.1-plant-foot")
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
    }*/


}


@Composable
fun LazyRowPlant01(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.g6_2_plant1),
            contentDescription = null,
        )
        Text(text = "9.1")

    }
   
}


