package com.example.testtabapp.Services

import com.example.testtabapp.DeleteUser.ResponseDelete
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.Path

interface DeleteUserService {

    @DELETE("public-api/users/{id}")
    fun deleteUser(@Path("id") int: Int, @Header("Authorization") authToken: String?) : Call<ResponseDelete>
}