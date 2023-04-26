package com.example.levelup.data.notification

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "announcements")
data class Announcement(
    @PrimaryKey
    var id: Int,
    @SerializedName("sent_by_name")
    var name: String,
    @SerializedName("sent_by_role")
    var role: String,
    var title: String,
    @SerializedName("description")
    var message: String,
    @SerializedName("created_at")
    var time: String,
)
