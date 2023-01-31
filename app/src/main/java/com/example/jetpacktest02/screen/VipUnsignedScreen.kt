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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpacktest02.R

/**
 * 会员页面
 * 负责人：
 */
@Preview(showBackground=true,widthDp=393,heightDp=851)
@Composable
fun VipUnsignedScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit={},

) {
    Column(modifier = Modifier.padding(top = 20.dp, start = 26.dp,end=26.dp),) {
        VipCardImage()
        Spacer(modifier = Modifier.height(14.dp))
        LazyColumn(

            verticalArrangement = Arrangement.spacedBy(14.dp),
            content = {
                item{
                    item1()
                }
                item{
                    item2()
                }
                item{
                    item3()
                }
                item{
                    item4()
                }
                item{
                    item5()
                }
            })
        Spacer(modifier = Modifier.height(14.dp))
        moreImg()
    }



}
@Composable
fun VipCardImage(){
    Image(
        painter = painterResource(id = R.drawable.g8_1_img_card),
        contentDescription = null,

    )

}
@Composable
fun item1(){
    Image(
        painter = painterResource(id = R.drawable.g8_1_v1),
        contentDescription = null,

    )

}
@Composable
fun item2(){
    Image(
        painter = painterResource(id = R.drawable.g8_1_v2),
        contentDescription = null,

    )

}
@Composable
fun item3(){
    Image(
        painter = painterResource(id = R.drawable.g8_1_v3),
        contentDescription = null,

    )

}
@Composable
fun item4(){
    Image(
        painter = painterResource(id = R.drawable.g8_1_v4),
        contentDescription = null,

    )

}
@Composable
fun item5(){
    Image(
        painter = painterResource(id = R.drawable.g8_1_v5),
        contentDescription = null,

    )

}
@Composable
fun moreImg(){
    Image(
        painter = painterResource(id = R.drawable.g8_1_more),
        contentDescription = null,

        )

}
