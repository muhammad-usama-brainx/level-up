package com.example.levelup.repo

import com.example.levelup.api.AuthApi
import com.example.levelup.data.user.User
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask


class AuthRepo(private val authApi: AuthApi) {

    suspend fun login(email: String, password: String): User? {
        val loginInfo = HashMap<String, String>()
        loginInfo["email"] = email
        loginInfo["password"] = password

        val result = authApi.login(loginInfo)
        if (result.body() != null && result.isSuccessful) {
           val user = result.body()
            user?.token = result.headers()["access-token"]!!
            user?.client = result.headers()["client"]!!
            return user
        }
        return null
    }

    suspend fun logout(user: User) : Boolean =
        authApi.logout(user.uid, user.token, user.client).isSuccessful





}