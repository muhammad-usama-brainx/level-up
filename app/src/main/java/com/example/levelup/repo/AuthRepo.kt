package com.example.levelup.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.levelup.api.AuthApi
import com.example.levelup.models.User
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask


class AuthRepo(private val authApi: AuthApi, private val context: Context) {

    private var userLiveData = MutableLiveData<User>()
    val user: LiveData<User>
        get() = userLiveData


    suspend fun login(email: String, password: String): Boolean {
        val loginInfo = HashMap<String, String>()
        loginInfo["email"] = email
        loginInfo["password"] = password

        val result = authApi.login(loginInfo)
        if (result?.body() != null && result.isSuccessful) {

            var user = result.body()
            user?.token = result.headers()["access-token"]!!
            user?.client = result.headers()["client"]!!

            userLiveData.postValue(user)

            //Saving data locally
            Timer()
                .schedule(object : TimerTask() {
                    override fun run() {
                        MainScope().launch {
                            saveLocally()
                        }
                    }
                }, 2000)
            return true
        }
        return false
    }

    suspend fun logout(): Boolean {
        return true
    }

    fun autoLogin(): Boolean {
        val prefs = context.applicationContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val id = prefs.getInt("id", -1)
        val uid = prefs.getString("uid", null)
        val name = prefs.getString("name", null)
        val email = prefs.getString("email", null)
        val token = prefs.getString("token", null)
        val client = prefs.getString("client", null)

        if (id != -1 && uid != null && name != null && email != null && token != null && client != null) {
            userLiveData.value = User(email, id, name, uid, token, client)
            return true
        }

        return false
    }

    private suspend fun saveLocally() {
        val prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val edit = prefs.edit()
        edit.putInt("id", user.value!!.id)
        edit.putString("uid", user.value!!.uid)
        edit.putString("name", user.value!!.name)
        edit.putString("email", user.value!!.email)
        edit.putString("token", user.value!!.token)
        edit.putString("client", user.value!!.token)
        edit.apply()
    }

    //Singleton
    companion object {
        private var instance: AuthRepo? = null

        fun getInstance(authApi: AuthApi, context: Context): AuthRepo {
            return instance ?: AuthRepo(authApi, context).apply {
                instance = this
            }
        }
    }
}