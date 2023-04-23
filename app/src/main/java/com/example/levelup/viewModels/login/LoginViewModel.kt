package com.example.levelup.viewModels.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelup.api.AuthApi
import com.example.levelup.models.User
import com.example.levelup.repo.AuthRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val authApi: AuthApi, private val context: Context) : ViewModel() {


    private val authRepo: AuthRepo = AuthRepo.getInstance(authApi, context)

    var isLoading = MutableLiveData<Boolean>(false)
    var error = MutableLiveData<String?>(null)
    val user: LiveData<User>
        get() = authRepo.user

    fun autoLogin() {
        isLoading.value = true
        authRepo.autoLogin()
        isLoading.value = false
    }

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)

            val isSuccessful = authRepo.login(email, password)
            if (!isSuccessful)
                error.postValue("Login failed. Please check credentials")

            isLoading.postValue(false)
        }
    }

    fun isValidInput(email: String, password: String): Boolean {
        if (!email.contains("@") || password.length < 6)
            return false
        return true
    }
}