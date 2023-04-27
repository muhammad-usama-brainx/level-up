package com.example.levelup.data.remote.api

import com.example.levelup.data.models.notification.Notification
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