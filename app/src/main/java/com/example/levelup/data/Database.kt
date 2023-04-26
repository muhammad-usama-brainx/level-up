package com.example.levelup.data

import androidx.room.RoomDatabase
import com.example.levelup.data.models.notification.Announcement
import com.example.levelup.data.models.notification.AnnouncementDao
import com.example.levelup.data.models.user.User
import com.example.levelup.data.models.user.UserDao

@androidx.room.Database(entities = [User::class, Announcement::class], version = 3)
abstract class Database : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun notificationDao(): AnnouncementDao
}
