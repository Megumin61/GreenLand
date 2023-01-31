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

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.ui.Modifier

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        modifier = Modifier.fillMaxSize()
    )
    Column(modifier = Modifier.
    fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        PlantImage()
        NamesBar()
        TabRowDemo()
    }
 

}

@Composable
fun PlantImage(){
    Image(painter = painterResource(
        id = R.drawable.g5_1_1_img_flower),
        contentDescription = null,
        modifier = Modifier
            .width(119.dp)
            .padding(top = 84.dp))

}
@Composable
fun NamesBar() {
    val names = listOf("已拥有", "商城")
    var selected by remember { mutableStateOf(0) }
    LazyRow(Modifier.padding(0.dp, 8.dp), contentPadding = PaddingValues(12.dp, 0.dp)) {
        itemsIndexed(names) { index, name ->
            Column(
                Modifier
                    .padding(12.dp, 4.dp)
                    .width(IntrinsicSize.Max)) {

                Text(name, fontSize = 15.sp,
                    color = if (index == selected) Color(0xfffa9e51) else Color(0xffb4b4b4)
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                        .height(2.dp)
                        .clip(RoundedCornerShape(1.dp))
                        .background(
                            if (index == selected) Color(0xfffa9e51) else Color.Transparent
                        )
                )
            }
        }
    }
}


@Composable
fun TabRowDemo() {
    var state = remember { mutableStateOf(0) }
    val titles = listOf("已拥有", "商城")

    Column {
        TabRow(selectedTabIndex = state.value,
            modifier = Modifier.height(50.dp),

                ) {

            titles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = state.value == index,
                    onClick = { state.value = index }
                )
            }
        }



    }
}








