package com.example.levelup.viewModels.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelup.api.AuthApi
import com.example.levelup.data.Database
import com.example.levelup.repo.AuthRepo
import kotlinx.coroutines.*

class SettingsFragmentViewModel(private val authApi: AuthApi,private val database: Database) :
    ViewModel() {

    private val authRepo = AuthRepo(authApi)
    var isLoading = MutableLiveData<Boolean>(false)

    suspend fun onLogout(): Boolean {
        val loggedInUser = database.userDao().getUser()!!

        val isLoggedOut = viewModelScope.async {
            isLoading.postValue(true)
            val isLoggedOut = authRepo.logout(loggedInUser)
            if(isLoggedOut)
                database.userDao().deleteUser(loggedInUser)
            isLoading.postValue(false)
            isLoggedOut
        }
        return isLoggedOut.await()
    }
}