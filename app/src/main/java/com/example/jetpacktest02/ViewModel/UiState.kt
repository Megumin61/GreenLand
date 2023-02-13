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

import android.text.BoringLayout
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.DeleteSurroundingTextInCodePointsCommand
import com.example.jetpacktest02.R
import com.example.jetpacktest02.screen.LocationDetails
import com.google.accompanist.pager.rememberPagerState
import java.time.Duration

/**
 * Data class that represents the UI state
 */
data class UiState constructor(

    var diyPlanName :MutableState<String> = mutableStateOf(  ""),
    var currentRoot: String = "",
    //MessageScreen
    val openDialog: MutableState<Boolean> = mutableStateOf(false),
    //MessageFriendScreen
    val pageState: MutableState<Int> = mutableStateOf(0),
    //ChooseSeedScreen
    val chooseSeedPageState: MutableState<Int> = mutableStateOf(0),
    var searchText: String = "",
    val waterValue:Int=0,
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
    var mePos: MutableState<LocationDetails> = mutableStateOf(LocationDetails(0.0, 0.0)),
    //IslandMemberListScreen
    var meVisible: MutableState<Boolean> = mutableStateOf(true),//用户是否被他人可见
    //IslandScreen
    var meItem: MutableState<FriendItem> = mutableStateOf(
        FriendItem(
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
    var showRequestFriendDialog: MutableState<Boolean> = mutableStateOf(false),
    var showImgMsgDialog: MutableState<Boolean> = mutableStateOf(false),
    var showTextMsg: MutableState<Boolean> = mutableStateOf(false),
    var visitItem: MutableState<ExploreMemberItem> = mutableStateOf(ExploreMemberItem()),
    var msgItem: MutableState<FriendItem> = mutableStateOf(FriendItem()),
    //好友岛页面所有的好友数据
    var friendListData: MutableList<FriendItem> = mutableStateListOf(
        FriendItem(
            userName = "megumin",
            userAvatar = R.drawable.g2_1_img_user01,
            userPlant = R.drawable.g4_2_img_grass_shadowed,
            offsetX = 1f,
            offsetY = 1f,
            textMsg = "",
            imgMsg = 0,
            onlineTime = "10分钟前来过", msgTime = ""
        ), FriendItem(
            userName = "skcccccccc",
            userAvatar = R.drawable.g2_1_img_user03,
            userPlant = R.drawable.g4_2_img_cactus_shadowed,
            offsetX = -1f,
            offsetY = -0.7f,
            textMsg = "",
            imgMsg = 0,
            onlineTime = "10分钟前来过", msgTime = ""
        ), FriendItem(
            userName = "foxbread",
            userAvatar = R.drawable.g2_1_img_user05,
            userPlant = R.drawable.g4_2_img_flower_shadowed,
            offsetX = 0.7f,
            offsetY = -1f,
            textMsg = "",
            imgMsg = R.drawable.g4_6_img_imgmsg,
            onlineTime = "10分钟前来过", msgTime = "20分钟前"
        ), FriendItem(
            userName = "sandro",
            userAvatar = R.drawable.g2_1_img_user02,
            userPlant = R.drawable.g4_2_img_cactus_shadowed,
            offsetX = -1.2f,
            offsetY = 1.3f,
            textMsg = "大家新年快乐鸭！",
            imgMsg = 0,
            onlineTime = "10分钟前来过", msgTime = "10分钟前"
        ),
        FriendItem(
            userName = "sanchooo",
            userAvatar = R.drawable.g2_1_img_user02,
            userPlant = R.drawable.g4_2_img_grass_shadowed,
            offsetX = -0.2f,
            offsetY = 1.65f,
            textMsg = "大家好！我的名字叫桑乔。",
            imgMsg = 0,
            onlineTime = "10分钟前来过", msgTime = "10分钟前"
        )
    ),

    //探索岛页面所有的好友数据
    var exploreMemberListData: MutableList<ExploreMemberItem> = mutableStateListOf(
        ExploreMemberItem(
            userName = "megumin",
            userAvatar = R.drawable.g2_1_img_user01,
            userPlant = R.drawable.g4_2_img_grass_shadowed,
            offsetX = 1f,
            offsetY = 1f,
            textMsg = "",
            imgMsg = 0,
            onlineTime = "10分钟前来过", msgTime = "", isFriend = true,
            location = LocationDetails(latitude = 23.173542, longitude = 113.253338)
        ), ExploreMemberItem(
            userName = "skcs1234",
            userAvatar = R.drawable.g2_1_img_user03,
            userPlant = R.drawable.g4_2_img_cactus_shadowed,
            offsetX = -1f,
            offsetY = -0.7f,
            textMsg = "",
            imgMsg = 0,
            onlineTime = "10分钟前来过", msgTime = "",
            location = LocationDetails(latitude = 23.17309, longitude = 113.253686)
        ), ExploreMemberItem(
            userName = "fox1234",
            userAvatar = R.drawable.g2_1_img_user05,
            userPlant = R.drawable.g4_2_img_flower_shadowed,
            offsetX = 0.7f,
            offsetY = -1f,
            textMsg = "",
            imgMsg = R.drawable.g4_6_img_imgmsg,
            onlineTime = "10分钟前来过", msgTime = "20分钟前",
            location = LocationDetails(latitude = 23.172914, longitude = 113.254578)
        ), ExploreMemberItem(
            userName = "1234",
            userAvatar = R.drawable.g2_1_img_user02,
            userPlant = R.drawable.g4_2_img_cactus_shadowed,
            offsetX = -1.2f,
            offsetY = 1.3f,
            textMsg = "大家新年快乐鸭！",
            imgMsg = 0,
            onlineTime = "10分钟前来过", msgTime = "10分钟前",
            location = LocationDetails(latitude = 23.173095, longitude = 113.254137)
        ),
        ExploreMemberItem(
            userName = "sanchooo",
            userAvatar = R.drawable.g2_1_img_user02,
            userPlant = R.drawable.g4_2_img_grass_shadowed,
            offsetX = -0.2f,
            offsetY = 1.65f,
            textMsg = "大家好！我的名字叫桑乔。",
            imgMsg = 0,
            onlineTime = "10分钟前来过",
            msgTime = "10分钟前",
            location = LocationDetails(latitude = 23.170444, longitude = 113.25302),
            isFriend = true
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
    var msgTime: String = ""
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
    var animVisible: Boolean = false
)

//消息，拍一拍消息列表对象
data class TapListItemModel(
    val name: String,
    val msg: String,
    var res: Int,
    var res2: Int,
    var time: String
)

//健康总结 各项健康指标的日数据
data class HealthConclusionItem(
    public var stepMonday:Int=200,//周一步数
    var stepTuesday:Int,//周二步数
    var stepWednesday:Int,//周三步数
    var stepThursday:Int,//周四步数
    var stepFriday:Int,//周五步数
    var stepSaturday:Int,//周六步数
    var stepSunday:Int,//周天步数
    var avgStep:Float=(stepMonday+stepTuesday+stepWednesday+stepThursday+stepFriday+stepFriday+stepSaturday+stepSunday)/7f,//一周平均步数

    var sitMonday:Int,//周一久坐时间
    var sitTuesday:Int,//周二久坐时间
    var sitWednesday:Int,//周三久坐时间
    var sitThursday:Int,//周四久坐时间
    var sitFriday:Int,//周五久坐时间
    var sitSaturday:Int,//周六久坐时间
    var sitSunday:Int,//周天久坐时间
    var avgSit:Float=(sitMonday+sitTuesday+sitWednesday+sitThursday+sitFriday+sitFriday+sitSaturday+sitSunday)/7f,//一周平均久坐时间


    var waterMonday:Int,//周一喝水次数
    var waterTuesday:Int,//周二喝水次数
    var waterWednesday:Int,//周三喝水次数
    var waterThursday:Int,//周四喝水次数
    var waterFriday:Int,//周五喝水次数
    var waterSaturday:Int,//周六喝水次数
    var waterSunday:Int,//周天喝水次数
    var avgWater:Float=(waterMonday+waterTuesday+waterWednesday+waterThursday+waterFriday+waterFriday+waterSaturday+waterSunday)/7f,//一周平均喝水次数

    var eatMonday:Int,//周一按时吃饭次数
    var eatTuesday:Int,//周二按时吃饭次数
    var eatWednesday:Int,//周三按时吃饭次数
    var eatThursday:Int,//周四按时吃饭次数
    var eatFriday:Int,//周五按时吃饭次数
    var eatSaturday:Int,//周六按时吃饭次数
    var eatSunday:Int,//周天按时吃饭次数
    var avgEat:Float=(eatMonday+eatTuesday+eatWednesday+eatThursday+eatFriday+eatFriday+eatSaturday+eatSunday)/7f,//一周平均按时吃饭次数

)
