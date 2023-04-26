package com.example.levelup.api

import com.example.levelup.data.notification.Notification
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface NotificationApi {

    @GET("/api/v1/announcements")
    suspend fun getNotifications(
        @Header("uid") uid: String,
        @Header("access-token") token: String,
        @Header("client") client: String,
    ): Response<Notification>
}