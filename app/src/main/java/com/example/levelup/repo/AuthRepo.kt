package com.example.levelup.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.levelup.api.AuthApi
import com.example.levelup.models.User


class AuthRepo(private val authApi: AuthApi) {

    private var userLiveData = MutableLiveData<User>()
    val user: LiveData<User>
        get() = userLiveData


    suspend fun login(email: String, password: String): Boolean {
        val loginInfo = HashMap<String, String>()
        loginInfo["email"] = email
        loginInfo["password"] = password

        val result = authApi.login(loginInfo)
        if (result?.body() != null && result.isSuccessful) {
            userLiveData.postValue(result.body())
            return true
        }
        return false
    }

    //Singleton
    companion object {
        private var instance: AuthRepo? = null

        fun getInstance(authApi: AuthApi): AuthRepo {
            return instance ?: AuthRepo(authApi).apply {
                instance = this
            }
        }
    }
}