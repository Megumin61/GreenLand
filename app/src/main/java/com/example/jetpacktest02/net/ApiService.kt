package com.example.jetpacktest02.net

import com.example.jetpacktest02.Entity.ZUser
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {


    /**
     * 用户列表
     */
    @POST("tagList")
    suspend fun userList(
    ): List<ZUser>

    /**
     * 新增用户
     */
    @POST("tag/insert")
    suspend fun userInsert(
        @Body requestBody: RequestBody
    ): Any?

    /**
     * 修改步数，传入用户id,步数step
     */
    @POST("tag/updateStep")
    suspend fun userUpdateStep(
        @Body requestBody: RequestBody
    ): Any?

    /**
     * 修改位置，传入用户id,位置pos
     */
    @POST("tag/updatePos")
    suspend fun userUpdatePos(
        @Body requestBody: RequestBody
    ): Any?

//    /**
//     * 历史上的今天
//     */
//    @GET(ApiAddress.HISTORY_DATE)
//    suspend fun history(
//        @Query("date") dtype: String,
//        @Query("key") key: String
//    ): Any?
//
//
//    /**
//     * get
//     */
//    @GET(ApiAddress.LOGIN)
//    suspend fun login(
//        @Query("account") account: String,
//        @Query("password") password: String
//    ): BaseResponse<Any>
//
//
//    /**
//     * post body
//     */
//    @POST(ApiAddress.LOGIN)
//    suspend fun loginBody(@Body requestBody: RequestBody): BaseResponse<Any>
}

private const val BASE_URL =
//    "http://10.0.2.2:28391/back/"
    "http://42.192.100.220:28391/back/"

/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */
@OptIn(ExperimentalSerializationApi::class)
private val retrofit = Retrofit.Builder()
    .addConverterFactory(
        GsonConverterFactory.create()
//        Json.asConverterFactory(MediaType.get("application/json"))
    )
    .baseUrl(BASE_URL)
    .build()

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object UserApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}