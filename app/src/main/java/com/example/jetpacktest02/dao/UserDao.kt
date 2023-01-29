package com.example.jetpacktest02.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jetpacktest02.Entity.User


@Dao
interface UserDao {
    @Query("SELECT * FROM user_table ORDER BY name ASC")
    fun getAlphabetizedWords(): kotlinx.coroutines.flow.Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User): Unit

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User)

    @Query("select * from user_table")
    fun getAllUser(): LiveData<List<User>>

    @Query("select * from user_table where name=:name and phoneNumber=:phoneNumber")
    fun queryUserByPhone(name:String,phoneNumber:String):User

}