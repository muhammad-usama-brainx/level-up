package com.example.levelup.ui.viewModels.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelup.api.AuthApi
import com.example.levelup.data.Database

class LoginViewModelFactory(private val authApi: AuthApi, private val database: Database) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(authApi, database) as T
    }
}