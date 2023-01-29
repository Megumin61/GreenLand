package com.example.jetpacktest02.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.jetpacktest02.Entity.User
import com.example.jetpacktest02.dao.UserDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class UserRepository(private val userDao: UserDao) {

    val allUsers: kotlinx.coroutines.flow.Flow<List<User>> = userDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    // Android-Room要求数据操作不能在UI线程中，需要在子线程中进行操作
    suspend fun insertUser(user: User) {
        GlobalScope.launch {
            userDao?.insertUser(user)
        }
    }

    fun deleteUserById(id: Int) {
        val user = User().apply {
            this.id = id
        }
        GlobalScope.launch {
            userDao?.deleteUser(user)
        }
    }

    fun updateUser(user:User){
        GlobalScope.launch {
            userDao?.updateUser(user)
        }
    }

    fun getAllUsers(): LiveData<List<User>>? {
        return userDao?.getAllUser()
    }


}