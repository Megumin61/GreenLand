package com.example.jetpacktest02.config

import android.app.Application
import com.example.jetpacktest02.Repository.UserRepository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class UsersApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts

    val database by lazy { UsersRoomDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { UserRepository(database.userDao()) }
}