package com.example.jetpacktest02.Repository

import android.os.AsyncTask
import android.provider.Contacts.People
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
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
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateUser(user:User){
            UsersApplication.database.userDao().updateUser(user)
    }

    fun getAllUsers(): LiveData<List<User>>? {
        return UsersApplication.database.userDao()?.getAllUser()
    }

    fun getUserById(id : Int): User {
        return UsersApplication.database.userDao().getUserById(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        UsersApplication.database.userDao().insertUser(user)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updatePositionById(position:String,id:Int) {
        UsersApplication.database.userDao().updatePositionById(position,id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateStepById(step:Int,id:Int) {
        UsersApplication.database.userDao().updateStepById(step,id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateWeekStepById(weekStep:Int,id:Int) {
        UsersApplication.database.userDao().updateWeekStepById(weekStep,id)
    }


}