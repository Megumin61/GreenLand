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

import android.hardware.Sensor
import android.hardware.SensorManager
import android.text.BoringLayout
import android.widget.MultiAutoCompleteTextView
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

import androidx.compose.ui.text.input.DeleteSurroundingTextInCodePointsCommand
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

import androidx.lifecycle.ViewModel
import com.example.jetpacktest02.Entity.ZUser

import com.example.jetpacktest02.R
import com.example.jetpacktest02.screen.LocationDetails
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.time.Duration

/**
 * Data class that represents the UI state
 */
data class UiState constructor(
    //Activity 轻提醒逻辑判断
    var isLockScreen :MutableState<Int> = mutableStateOf(0),
    //用户所选择的头像
    var avartarInt :MutableState<Int> = mutableStateOf(0),

    //注册账号相关
    var userList :MutableList<ZUser> = mutableStateListOf(),

    //发布私信/图片页面模式 （留言模式:0 / 照片模式:1）
    var deliverPageMode :MutableState<Int> = mutableStateOf(0),

    //运动步数 变量
    var stepDetector: MutableState<Int> = mutableStateOf(0), // 自应用运行以来STEP_DETECTOR检测到的步数
    var stepCounter: MutableState<Int> = mutableStateOf(0), // 自系统开机以来STEP_COUNTER检测到的步数
    var currentDate: MutableState<String> = mutableStateOf(""),
    var currentStep: MutableState<Int> = mutableStateOf(0),
    var sensorManager: SensorManager? = null,
    val hasRecord: MutableState<Boolean> = mutableStateOf(false),
    //未记录之前的步数
    var hasStepCount: MutableState<Int> = mutableStateOf(0),
    //下次记录之前的步数
    var previousStepCount: MutableState<Int> = mutableStateOf(0),


    var diyPlanName: MutableState<String> = mutableStateOf(""),
    var currentRoot: String = "",
    //MessageScreen
    val openDialog: MutableState<Boolean> = mutableStateOf(false),
    //MessageFriendScreen
    val pageState: MutableState<Int> = mutableStateOf(0),
    //PlantScreen
    val PlantPage: MutableState<Int> = mutableStateOf(0),
    var isGrowUp: MutableState<Int> = mutableStateOf(0),
    var plantExp: MutableState<Int> = mutableStateOf(60),

    //ChooseSeedScreen
    val chooseSeedPageState: MutableState<Int> = mutableStateOf(0),
    //用户选择的植物类型
    val flowerid:MutableState<Int> = mutableStateOf(0),
    //用户选择的植物类型
    val isVip:MutableState<Boolean> = mutableStateOf(false),
    var searchText: String = "",
    val waterValue: Int = 56,
    val tabMessageList: MutableList<TapListItemModel> =
        mutableStateListOf(
            //如果需要改变下面对象里面的属性，需要单独复制一份生成一个新的对象才可以
            TapListItemModel(
                "幻想世界",
                "拍了拍我",
                R.drawable.userprofile_1,
                R.drawable.g2_1_btn_friend,
                "1min前"
            ),
            TapListItemModel(
                "sandr",
                "拍了拍我",
                R.drawable.userprofile_2,
                R.drawable.g2_1_btn_friend_disabled,
                "5min前"
            ),
            TapListItemModel(
                "施&SHI",
                "拍了拍我",
                R.drawable.userprofile_3,
                R.drawable.g2_1_btn_friend,
                "5min前"
            ),
            TapListItemModel(
                "ajunGrit",
                "拍了拍我",
                R.drawable.userprofile_4,
                R.drawable.g2_1_btn_friend_disabled,
                "12-01"
            ),
            TapListItemModel(
                "ajunGrit",
                "拍了拍我",
                R.drawable.userprofile_5,
                R.drawable.g2_1_btn_friend_disabled,
                "12-01"
            ),
            TapListItemModel(
                "ajunGrit",
                "拍了拍我",
                R.drawable.userprofile_6,
                R.drawable.g2_1_btn_friend_disabled,
                "12-01"
            ),

            ),

    //用户本人的经纬度位置，数据类型为Double
    var mePos: MutableState<LocationDetails> = mutableStateOf(LocationDetails(23.050261, 113.40672)),
    //IslandMemberListScreen
    var meVisible: MutableState<Boolean> = mutableStateOf(true),//用户是否被他人可见
    //IslandScreen
    var meItem: MutableState<FriendItem> = mutableStateOf(
        FriendItem(
            userName = "ajunGrit", //本人用户名
            userAvatar = R.drawable.userprofile_12, //本人头像
            userPlant = R.drawable.gif_4, //本人植物
            offsetX = 0f,
            offsetY = 0f,
            textMsg = "",
            imgMsg = 0,
            onlineTime = "10分钟前来过", msgTime = ""
        )
    ),
    var showRequestFriendDialog: MutableState<Boolean> = mutableStateOf(false),
    var showImgMsgDialog: MutableState<Boolean> = mutableStateOf(false),
    var showTextMsg: MutableState<Boolean> = mutableStateOf(false),
    var visitItem: MutableState<ExploreMemberItem> = mutableStateOf(ExploreMemberItem()),
    var msgItem: MutableState<FriendItem> = mutableStateOf(FriendItem()),
    //好友岛页面所有的好友数据
    var friendListData: MutableList<FriendItem> = mutableStateListOf(
        FriendItem(
            userName = "蹦蹦炸弹",
            userAvatar = R.drawable.g2_1_img_user01,
            userPlant = R.drawable.gif_6,
            offsetX = 1f,
            offsetY = 1f,
            textMsg = "",
            imgMsg = 0,
            onlineTime = "在线", msgTime = "", gifSize = 65.dp
        ), FriendItem(
            userName = "施公子驾到",
            userAvatar = R.drawable.g2_1_img_user03,
            userPlant = R.drawable.gif_1,
            offsetX = -1f,
            offsetY = -0.7f,
            textMsg = "",
            imgMsg = 0,
            onlineTime = "在线", msgTime = "", gifSize = 73.dp
        ), FriendItem(
            userName = "夜兰天下第一",
            userAvatar = R.drawable.g2_1_img_user05,
            userPlant = R.drawable.gif_3,
            offsetX = 0.7f,
            offsetY = -1f,
            textMsg = "",
            imgMsg = R.drawable.imgmsg1,
            onlineTime = "23分钟前来过", msgTime = "20分钟前", gifSize = 65.dp
        ), FriendItem(
            userName = "老默我想吃鱼",
            userAvatar = R.drawable.g2_1_img_user02,
            userPlant = R.drawable.gif_2,
            offsetX = -1.2f,
            offsetY = 1.3f,
            textMsg = "大家新年快乐鸭！",
            imgMsg = 0,
            onlineTime = "2分钟前来过", msgTime = "10分钟前", gifSize = 65.dp
        ),
        FriendItem(
            userName = "原味大肠",
            userAvatar = R.drawable.g2_1_img_user04,
            userPlant = R.drawable.gif_5,
            offsetX = -0.2f,
            offsetY = 1.65f,
            textMsg = "大家好！认识我的人都叫我华工C罗。",
            imgMsg = 0,
            onlineTime = "1小时前来过", msgTime = "15分钟前", gifSize = 50.dp
        )
    ),

    //探索岛页面所有的好友数据
    var exploreMemberListData: MutableList<ExploreMemberItem> = mutableStateListOf(
        ExploreMemberItem(
            userName = "痛却不说话",
            userAvatar = R.drawable.userprofile_3,
            userPlant = R.drawable.gif_1,
            offsetX = 1f,
            offsetY = 1f,
            textMsg = "",
            imgMsg = 0,
            onlineTime = "1小时前来过", msgTime = "", isFriend = true,
            location = LocationDetails(latitude = 23.050395, longitude = 113.406666), animDuration = 1000
        ), ExploreMemberItem(
            userName = "雾之恋",
            userAvatar = R.drawable.userprofile_6,
            userPlant = R.drawable.gif_2,
            offsetX = -1f,
            offsetY = -0.7f,
            textMsg = "",
            imgMsg = 0,
            onlineTime = "2小时前来过", msgTime = "",
            location = LocationDetails(latitude = 23.050284, longitude = 113.407752), animDuration = 800
        ), ExploreMemberItem(
            userName = "沦陷の痛",
            userAvatar = R.drawable.userprofile_13,
            userPlant = R.drawable.gif_3,
            offsetX = 0.7f,
            offsetY = -1f,
            textMsg = "",
            imgMsg = R.drawable.imgmsg2,
            onlineTime = "1分钟前来过", msgTime = "33分钟前",
            location = LocationDetails(latitude = 23.050359, longitude = 113.407768),animDuration = 500
        ), ExploreMemberItem(
            userName = "(*^_^*)",
            userAvatar = R.drawable.userprofile_16,
            userPlant = R.drawable.gif_6,
            offsetX = -1.2f,
            offsetY = 1.3f,
            textMsg = "大家开学快乐鸭！",
            imgMsg = 0,
            onlineTime = "30分钟前来过", msgTime = "4分钟前",
            location = LocationDetails(latitude = 23.050191, longitude = 113.40692),animDuration=600
        ),
        ExploreMemberItem(
            userName = "不再犹豫",
            userAvatar = R.drawable.userprofile_12,
            userPlant = R.drawable.gif_5,
            offsetX = -0.2f,
            offsetY = 1.65f,
            textMsg = "家人们，考试周顶得住吗",
            imgMsg = 0,
            onlineTime = "23分钟前来过",
            msgTime = "10分钟前",
            location = LocationDetails(latitude = 23.050008, longitude = 113.406629),
            isFriend = true,animDuration=200
        )
    ),


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
data class FriendItem(
    var userName: String = "", //用户名
    var userAvatar: Int = 0,//用户头像
    var userPlant: Int = 0,//用户植物图片
    var offsetX: Float = 0f,//植物位置偏移
    var offsetY: Float = 0f,//植物位置偏移
    var textMsg: String = "",//文字消息
    var imgMsg: Int = 0,//图片消息
    var onlineTime: String = "",
    var msgTime: String = "",
    var gifSize :Dp = 90.dp
)

//探索岛 岛友列表子项
data class ExploreMemberItem(
    var userName: String = "", //用户名
    var userAvatar: Int = 0,//用户头像
    var userPlant: Int = 0,//用户植物图片
    var offsetX: Float = 0f,//植物位置偏移
    var offsetY: Float = 0f,//植物位置偏移
    var textMsg: String = "",//文字消息
    var imgMsg: Int = 0,//图片消息
    var onlineTime: String = "",
    var msgTime: String = "",
    var distance: Double = 0.0,
    var isFriend: Boolean = false,
    var location: LocationDetails = LocationDetails(latitude = 0.0, longitude = 0.0),
    var animVisible: Boolean = false,
    var animDuration :Int =400
)

//消息，拍一拍消息列表对象
data class TapListItemModel(
    val name: String,
    val msg: String,
    var res: Int,
    var res2: Int,
    var time: String
)

