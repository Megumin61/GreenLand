package com.example.jetpacktest02.config

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

import com.example.jetpacktest02.Repository.UserRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class UsersApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts

//    val database by lazy { UsersRoomDatabase.getDatabase(this, applicationScope) }
//    val repository by lazy { UserRepository(database.userDao()) }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        lateinit var database: UsersRoomDatabase
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: UsersApplication
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        database = UsersRoomDatabase.getDatabase(context,applicationScope)
        instance = this
    }
}