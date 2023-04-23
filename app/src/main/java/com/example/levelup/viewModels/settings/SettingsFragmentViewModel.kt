package com.example.levelup.viewModels.settings

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelup.api.AuthApi
import com.example.levelup.repo.AuthRepo
import kotlinx.coroutines.*

class SettingsFragmentViewModel(private val authApi: AuthApi, private val context: Context) :
    ViewModel() {

    private val authRepo = AuthRepo.getInstance(authApi, context)
    var isLoading = MutableLiveData<Boolean>(false)

    suspend fun onLogout(): Boolean {


        val isLoggedOut = viewModelScope.async {
            isLoading.postValue(true)
            val result = authRepo.logout()
            delay(3000)
            isLoading.postValue(false)
            result
        }


        return isLoggedOut.await()
    }
}