package com.example.levelup.data.repo

import com.example.levelup.api.NotificationApi
import com.example.levelup.data.models.notification.Announcement
import com.example.levelup.data.models.user.User

class NotificationRepo(private val notificationApi: NotificationApi) {

    suspend fun getNotifications(user: User): List<Announcement>? {

        val response = notificationApi.getNotifications(user.uid, user.token, user.client)

        return if (response.body() != null && response.isSuccessful) {
            response.body()!!.announcements
        } else
            null
    }

}