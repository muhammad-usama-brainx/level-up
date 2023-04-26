package com.example.levelup

import android.app.Application
import androidx.room.Room
import com.example.levelup.data.Database

class MyApp : Application() {
    private lateinit var database: Database
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, Database::class.java, "database")
            .build()
    }

    fun databaseInstance(): Database = database
}