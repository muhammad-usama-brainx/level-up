package com.example.levelup.viewModels.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelup.api.AuthApi
import com.example.levelup.data.Database

class HomeFragmentViewModelFactory(private val authApi: AuthApi, private val database: Database) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(authApi, database) as T
    }
}