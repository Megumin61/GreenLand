package com.example.jetpacktest02.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class User() {
    @PrimaryKey(autoGenerate = true)//设置为主键，自动增长
    var id: Int = 0

    @ColumnInfo(name = "name")//别名 数据库中的名字如果不设置，默认是属性名称
    lateinit var name: String

    @ColumnInfo(name = "phoneNumber")
    lateinit var phoneNumber: String

    //次构造
//    @Ignore
    constructor(id :Int,name: String, phoneNumber: String) : this() {
        this.id = id
        this.name = name
        this.phoneNumber = phoneNumber
    }

    //次构造
    @Ignore
    constructor(name: String, phoneNumber: String) : this() {
        this.name = name
        this.phoneNumber = phoneNumber
    }
}

