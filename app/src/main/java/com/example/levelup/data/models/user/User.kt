package com.example.levelup.data.models.user

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(
    @PrimaryKey
    var id: Int,
    var email: String,
    var name: String,
    var uid: String,
    var token: String,
    var client: String
)
