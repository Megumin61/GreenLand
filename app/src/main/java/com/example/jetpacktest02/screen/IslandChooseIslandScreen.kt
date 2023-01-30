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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpacktest02.R

/**
 * 选择岛屿页面
 * 负责人：谭家俊
 */
@Composable
fun IslandChooseIslandScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit = {},

    ) {
    Image(
    painter = painterResource(id = R.drawable.g4_1_bg),
    contentDescription = null,
    modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
)
    Column {
        Image(
            painter = painterResource(id = R.drawable.g4_1_bn_friendisland),
            contentDescription = null,
            modifier = Modifier
                .width(377.dp)
                .height(230.dp)
                .align(Alignment.CenterHorizontally),
        )
        Image(
            painter = painterResource(id = R.drawable.g4_1_bn_exploreisland),
            contentDescription = null,
            modifier = Modifier
                .width(314.dp)
                .height(192.dp)
        )
//        Text("4.1-island-chooseIsland")
//        Button(
//            onClick = nav01,
//            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
//        ) {
//            Icon(
//                Icons.Filled.Favorite,
//                contentDescription = "Localized description",
//                modifier = Modifier.size(ButtonDefaults.IconSize)
//            )
//            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//            Text("1.1-Plant")
//        }
    }


}
