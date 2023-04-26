package com.example.levelup.data.models.notification

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface AnnouncementDao {

    @Insert
    suspend fun insertAll(notifications: List<Announcement>)

    @Delete
    suspend fun deleteAll(notifications: List<Announcement>)
}