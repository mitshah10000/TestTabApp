package com.example.testtabapp.RoomDatabase

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao){

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allUsers: LiveData<List<User>> = userDao.getUsers()

    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    fun delete(user: User) {
        userDao.delete(user)
    }

//    fun updateAlarm(user: User) {
//        userDao.update(user)
//    }

}
