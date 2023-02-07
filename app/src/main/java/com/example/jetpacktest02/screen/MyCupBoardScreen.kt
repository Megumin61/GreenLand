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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.jetpacktest02.screen.HealthcustomTabIndicatorOffset
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

/**
 * The Bills screen.
 */
@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground=true,widthDp=393,heightDp=851)
@Composable
fun MyCupBoardScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit={},

) {
    Box(
        modifier = Modifier
            .fillMaxSize()

            .background(

                brush = Brush.verticalGradient(
                    listOf(
                        Color(187, 238, 232),
                        Color(227, 237, 227)
                    )
                )
            ),

        contentAlignment = Alignment.TopCenter
    ){
        HorizontalPager(count = 10) { page ->
            Text(text = "Page: $page")
        }
        // 水平指示器
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
        )

// 垂直指示器



        }
    }


}


/* Column{
     Text("7.0-My")
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





