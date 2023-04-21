package com.example.levelup.viewModels.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelup.api.AuthApi

class LoginViewModelFactory(private val authApi: AuthApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(authApi) as T
    }
}