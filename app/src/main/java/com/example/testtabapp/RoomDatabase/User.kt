package com.example.testtabapp.RoomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_table")
class User(

    @PrimaryKey(autoGenerate = true)
    val alarmId: Int = 0,

    @ColumnInfo(name = "username")
    var userName: String?,

) : Serializable