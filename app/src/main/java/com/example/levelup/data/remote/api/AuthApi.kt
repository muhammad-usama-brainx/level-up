package com.example.levelup.data.remote.api


import com.example.levelup.data.models.user.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("api/v1/users/sign_in.json")
    suspend fun login(@Body loginInfo: HashMap<String, String>): Response<User>

    @DELETE("/api/v1/users/sign_out.json")
    suspend fun logout(
        @Header("uid") uid: String,
        @Header("access-token") token: String,
        @Header("client") client: String,
    ): Response<Any>
}