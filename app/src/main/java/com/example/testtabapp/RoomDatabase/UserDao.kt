package com.example.testtabapp.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

        @Query("SELECT * from user_table")
        fun getUsers(): LiveData<List<User>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun insert(user: User)

//        @Query("DELETE FROM alarm_table")
//        fun deleteAll()

        @Delete
        fun delete(user: User)

//        @Update
//        fun update(alarm: Alarm?)

}