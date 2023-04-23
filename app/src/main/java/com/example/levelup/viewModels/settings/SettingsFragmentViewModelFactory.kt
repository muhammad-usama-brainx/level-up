package com.example.levelup.viewModels.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelup.api.AuthApi

class SettingsFragmentViewModelFactory(private val authApi: AuthApi, private val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsFragmentViewModel(authApi, context) as T
    }
}