package com.example.levelup.data

import androidx.room.RoomDatabase
import com.example.levelup.data.notification.Announcement
import com.example.levelup.data.notification.AnnouncementDao
import com.example.levelup.data.notification.Notification
import com.example.levelup.data.user.User
import com.example.levelup.data.user.UserDao

@androidx.room.Database(entities = [User::class, Announcement::class], version = 3)
abstract class Database : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun notificationDao(): AnnouncementDao
}