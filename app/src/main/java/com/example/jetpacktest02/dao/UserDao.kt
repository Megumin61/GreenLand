package com.example.jetpacktest02.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jetpacktest02.Entity.User


@Dao
interface UserDao {
    @Query("SELECT * FROM user_table ORDER BY name ASC")
    fun getAlphabetizedUsers(): kotlinx.coroutines.flow.Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User): Unit

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User)

    @Query("UPDATE user_table SET position =:position WHERE id =:id")
    fun updatePositionById(position:String,id:Int)

    @Query("UPDATE user_table SET step =:step WHERE id =:id")
    fun updateStepById(step:Int,id:Int)

    @Query("select * from user_table")
    fun getAllUser(): LiveData<List<User>>

    @Query("select * from user_table where id =:id")
    fun getUserById(id : Int):User

    @Query("select * from user_table where phoneNumber =:phoneNumber")
    fun getUserByPhone(phoneNumber : String):User


}