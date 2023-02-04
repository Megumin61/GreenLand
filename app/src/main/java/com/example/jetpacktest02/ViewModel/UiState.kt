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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.time.Duration

/**
 * Data class that represents the UI state
 */
data class UiState(
    val currentScrambledWord: String = "",
    val currentWordCount: Int = 0,
    val score: Int = 0,
    val isGuessedWordWrong: Boolean = false,
    val isGameOver: Boolean = false,


    //MessageScreen
    val openDialog: MutableState<Boolean> = mutableStateOf(false)


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

//好友岛页面 用户数据
data class PlantModelState(
    val userName: String = "",//用户名
    val plantType: String = "",//用户当前的植物品种
    val msg: String = "",//用户发布的文字消息
    val hasRead: Boolean = false//用户发布的文字消息是否已读,若已读，将消除植物右上角小红点
)
