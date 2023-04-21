package com.example.levelup.api

import com.example.levelup.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/v1/users/sign_in.json")
    suspend fun login(@Body loginInfo: HashMap<String, String>): Response<User>
}