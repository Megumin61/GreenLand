package com.example.jetpacktest02.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import com.example.jetpacktest02.Entity.User
import com.example.jetpacktest02.config.UsersApplication
import com.example.jetpacktest02.dao.UserDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepository @Inject constructor() {
    //private val userDao: UserDao
    //Repository负责LiveData变量生成以及数据处理
    val allUsers: kotlinx.coroutines.flow.Flow<List<User>> = UsersApplication.database.userDao().getAlphabetizedUsers()
//    var user: User? = null
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    // Android-Room要求数据操作不能在UI线程中，需要在子线程中进行操作
    suspend fun insertUser(user: User) {
        GlobalScope.launch {
            UsersApplication.database.userDao()?.insertUser(user)

        }
    }

    fun deleteUserById(id: Int) {
        val user = User().apply {
            this.id = id
        }
        GlobalScope.launch {
            UsersApplication.database.userDao()?.deleteUser(user)
        }
    }

    fun updateUser(user:User){
        GlobalScope.launch {
            UsersApplication.database.userDao()?.updateUser(user)
        }
    }

    fun getAllUsers(): LiveData<List<User>>? {
        return UsersApplication.database.userDao()?.getAllUser()
    }

    fun getUserById(id : Int): User {
        return UsersApplication.database.userDao().getUserById(id)
    }


}