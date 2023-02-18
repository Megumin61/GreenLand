package com.example.jetpacktest02.config

import android.content.Context
import androidx.compose.ui.text.android.animation.SegmentType
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.jetpacktest02.Entity.User
import com.example.jetpacktest02.dao.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.chrono.HijrahChronology
import java.time.chrono.HijrahChronology.INSTANCE

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(User::class), version = 1,exportSchema = false)
abstract class UsersRoomDatabase : RoomDatabase() {

    // 定义的抽象方法，由Room自动实现
    abstract fun userDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        //单例模式
        @Volatile
        private var INSTANCE: UsersRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): UsersRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsersRoomDatabase::class.java,
                    "user_database"
                ).addCallback(UserDatabaseCallback(scope))
                    .allowMainThreadQueries()
                    .build()
                instance.getOpenHelper().getWritableDatabase()
                INSTANCE = instance
                // return instance
                instance

            }

        }
    }

    private class UserDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.userDao())
                }
            }
        }

        fun populateDatabase(userDao: UserDao) {
            // Delete all content here.
            userDao.deleteAll()
            println("创建成功=====================================================")
            // Add sample words.
            var user = User("Hello", "18329295231")
            userDao.insertUser(user)

            // TODO: Add your own words!
        }
    }
}

