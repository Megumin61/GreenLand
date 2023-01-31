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
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpacktest02.R

/**
 * 植物背包132123
 * 植物背包页面
 * 负责人：方凯荣
 * 对接人：
 */
@Preview(showBackground=true,widthDp=393,heightDp=851)
@Composable
fun PlantBagPossessedScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit={},

) {
    Image(
        painter = painterResource(id = R.drawable.g5_1_1_bg),
        contentDescription = null,
        modifier = Modifier
            .width(393.dp)
            .height((851.dp))
    )
    Column(modifier = Modifier.
    fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        PlantImage()
    }
 

}

@Composable
fun PlantImage(){
    Image(painter = painterResource(
        id = R.drawable.g5_1_1_img_flower),
        contentDescription = null,
        modifier = Modifier.width(119.dp))

}

