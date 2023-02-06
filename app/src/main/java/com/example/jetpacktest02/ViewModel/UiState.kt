/*
 * Copyright (c)2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jetpacktest02.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.example.jetpacktest02.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import java.time.Duration

/**
 * Data class that represents the UI state
 */
data class UiState constructor(
    var currentRoot: String = "",
    //MessageScreen
    val openDialog: MutableState<Boolean> = mutableStateOf(false),
    //MessageFriendScreen
    val pageState: MutableState<Int> = mutableStateOf(0),
    var searchText: String = "",


    //IslandMemberListScreen
    val meVisible: MutableState<Boolean> = mutableStateOf(true),//用户是否被他人可见
    //IslandScreen
    val meItem: MutableState<friendItem> = mutableStateOf(
        friendItem(
            userName = "ajunGrit", //本人用户名
            userAvatar = R.drawable.g2_1_img_user04, //本人头像
            userPlant = R.drawable.g4_2_img_flower_shadowed, //本人植物
            offsetX = 0f,
            offsetY = 0f,
            textMsg = "",
            imgMsg = 0,
            onlineTime = "10分钟前来过", msgTime = ""
        )
    ),
    val showImgMsgDialog: MutableState<Boolean> = mutableStateOf(false),
    val showTextMsg: MutableState<Boolean> = mutableStateOf(false),
    val msgItem: MutableState<friendItem> = mutableStateOf(friendItem()),
    val friendListData: MutableList<friendItem> = mutableStateListOf(
        friendItem(
            userName = "megumin",
            userAvatar = R.drawable.g2_1_img_user01,
            userPlant = R.drawable.g4_2_img_grass_shadowed,
            offsetX = 1f,
            offsetY = 1f,
            textMsg = "",
            imgMsg = 0,
            onlineTime = "10分钟前来过", msgTime = ""
        ), friendItem(
            userName = "skcccccccc",
            userAvatar = R.drawable.g2_1_img_user03,
            userPlant = R.drawable.g4_2_img_cactus_shadowed,
            offsetX = -1f,
            offsetY = -0.7f,
            textMsg = "",
            imgMsg = 0,
            onlineTime = "10分钟前来过", msgTime = ""
        ), friendItem(
            userName = "foxbread",
            userAvatar = R.drawable.g2_1_img_user05,
            userPlant = R.drawable.g4_2_img_flower_shadowed,
            offsetX = 0.7f,
            offsetY = -1f,
            textMsg = "",
            imgMsg = R.drawable.g4_6_img_imgmsg,
            onlineTime = "10分钟前来过", msgTime = "20分钟前"
        ), friendItem(
            userName = "sandro",
            userAvatar = R.drawable.g2_1_img_user02,
            userPlant = R.drawable.g4_2_img_cactus_shadowed,
            offsetX = -1.2f,
            offsetY = 1.3f,
            textMsg = "大家新年快乐鸭！",
            imgMsg = 0,
            onlineTime = "10分钟前来过", msgTime = "10分钟前"
        ),
        friendItem(
            userName = "sanchooo",
            userAvatar = R.drawable.g2_1_img_user02,
            userPlant = R.drawable.g4_2_img_grass_shadowed,
            offsetX = -0.2f,
            offsetY = 1.65f,
            textMsg = "大家好！我的名字叫桑乔。",
            imgMsg = 0,
            onlineTime = "10分钟前来过", msgTime = "10分钟前"
        )

    )

)

data class PlayerUiState(
    val title: String = "",
    val subTitle: String = "",
    val duration: Duration? = null,
    val podcastName: String = "",
    val author: String = "",
    val summary: String = "",
    val podcastImageUrl: String = ""
)

data class Plant(
    val water: Int = 100
)

//好友岛 好友列表子项
data class friendItem(
    val userName: String = "", //用户名
    val userAvatar: Int = 0,//用户头像
    val userPlant: Int = 0,//用户植物图片
    val offsetX: Float = 0f,//植物位置偏移
    val offsetY: Float = 0f,//植物位置偏移
    val textMsg: String = "",//文字消息
    val imgMsg: Int = 0,//图片消息
    val onlineTime: String = "",
    val msgTime: String = ""
)