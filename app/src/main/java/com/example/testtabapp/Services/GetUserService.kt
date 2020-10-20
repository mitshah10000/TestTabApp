package com.example.testtabapp.Services

import com.example.testtabapp.UserInfo.Users
import retrofit2.Call
import retrofit2.http.POST

interface GetUserService {

    @POST("public-api/users")
    fun getUser() : Call<Users>
}