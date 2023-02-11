/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jetpacktest02.ViewModel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpacktest02.Entity.ZUser
import com.example.jetpacktest02.config.UsersApplication
import com.example.jetpacktest02.net.UserApi
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.IOException

///**
// * UI state for the Home screen
// */
//sealed interface MarsUiState {
//    data class Success(val result: String) : MarsUiState
//    data class Error(val result: String) : MarsUiState
//    object Loading : MarsUiState
//}

class MarsViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
//    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
//        private set
    var listResult:List<ZUser> = listOf()

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getUserList()
    }

    fun getUserList(): List<ZUser> {
        viewModelScope.launch {
            try {
                listResult=UserApi.retrofitService.userList()
            } catch (e: IOException) {
                Toast.makeText(UsersApplication.context,e.message,Toast.LENGTH_SHORT).show()
            } catch (e: HttpException) {
                Toast.makeText(UsersApplication.context,e.message,Toast.LENGTH_SHORT).show()
            }
        }
        return listResult
    }

    fun addUser(username:String,phone:String){
        val json:String = "{\n" +
                "    \"username\":\"${username}\",\n" +
                "    \"phone\":\"${phone}\"\n" +
                "}"
        val body = RequestBody.create(
            MediaType.parse("application/json"), json
        )

        viewModelScope.launch {
            try {
                UserApi.retrofitService.userInsert(body)
            } catch (e: IOException) {
                Toast.makeText(UsersApplication.context,e.message,Toast.LENGTH_SHORT).show()
            } catch (e: HttpException) {
                Toast.makeText(UsersApplication.context,e.message,Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateStep(id:Int,step:String){
        val json:String = "{\n" +
                "    \"id\":\"${id}\",\n" +
                "    \"step\":\"${step}\"\n" +
                "}"
        val body = RequestBody.create(
            MediaType.parse("application/json"), json
        )

        viewModelScope.launch {
            try {
                UserApi.retrofitService.userUpdateStep(body)
            } catch (e: IOException) {
                Toast.makeText(UsersApplication.context,e.message,Toast.LENGTH_SHORT).show()
            } catch (e: HttpException) {
                Toast.makeText(UsersApplication.context,e.message,Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updatePos(id:Int,pos:String){
        val json:String = "{\n" +
                "    \"id\":\"${id}\",\n" +
                "    \"step\":\"${pos}\"\n" +
                "}"
        val body = RequestBody.create(
            MediaType.parse("application/json"), json
        )

        viewModelScope.launch {
            try {
                UserApi.retrofitService.userUpdatePos(body)
            } catch (e: IOException) {
                Toast.makeText(UsersApplication.context,e.message,Toast.LENGTH_SHORT).show()
            } catch (e: HttpException) {
                Toast.makeText(UsersApplication.context,e.message,Toast.LENGTH_SHORT).show()
            }
        }
    }
}
