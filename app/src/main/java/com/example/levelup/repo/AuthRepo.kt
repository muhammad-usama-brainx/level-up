package com.example.levelup.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.levelup.api.AuthApi
import com.example.levelup.models.User


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
            userLiveData.postValue(result.body())
            saveLocally(result.body()!!)
            return true
        }
        return false
    }

    fun autoLogin(): Boolean {
        val prefs = context.applicationContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val id = prefs.getInt("id", -1)
        val uid = prefs.getString("uid", null)
        val name = prefs.getString("name", null)
        val email = prefs.getString("email", null)

        if (id != -1 && uid != null && name != null && email != null) {
            userLiveData.value = User(email, id, name, uid)
            return true
        }

        return false
    }

    private suspend fun saveLocally(user: User) {
        val prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val edit = prefs.edit()
        edit.putInt("id", user.id)
        edit.putString("uid", user.uid)
        edit.putString("name", user.name)
        edit.putString("email", user.email)
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