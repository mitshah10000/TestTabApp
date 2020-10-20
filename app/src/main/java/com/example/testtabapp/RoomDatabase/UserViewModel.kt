package com.example.testtabapp.RoomDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import androidx.lifecycle.viewModelScope


class UserViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    val allUsers: LiveData<List<User>>
    private val scope = CoroutineScope(coroutineContext)

    init {
        val userDao = UserRoomDatabase.getDatabase(application, viewModelScope).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers
    }

    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(user)
    }

    fun delete(user: User) = scope.launch(Dispatchers.IO) {
        repository.delete(user)
    }
}
