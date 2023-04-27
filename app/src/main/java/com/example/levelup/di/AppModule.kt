package com.example.levelup.di

import android.app.Application
import androidx.room.Room
import com.example.levelup.data.remote.api.AuthApi
import com.example.levelup.data.remote.api.NotificationApi
import com.example.levelup.data.Database
import com.example.levelup.data.repo.AuthRepo
import com.example.levelup.data.repo.NotificationRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getAuthApi(): AuthApi = Retrofit.Builder()
        .baseUrl("https://staging.cblevelup.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthApi::class.java)


    @Provides
    @Singleton
    fun getAuthRepo(authApi: AuthApi): AuthRepo =
        AuthRepo(authApi)


    @Provides
    @Singleton
    fun getNotificationApi(): NotificationApi =
        Retrofit.Builder()
            .baseUrl("https://staging.cblevelup.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NotificationApi::class.java)

    @Provides
    @Singleton
    fun getNotificationRepo(notificationApi: NotificationApi): NotificationRepo =
        NotificationRepo(notificationApi)

    @Provides
    @Singleton
    fun getDb(app: Application): Database = Room.databaseBuilder(
        app,
        Database::class.java,
        "database"
    ).build()


}