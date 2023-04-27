package com.example.levelup.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelup.data.remote.api.AuthApi
import com.example.levelup.data.Database
import com.example.levelup.data.repo.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SettingsFragmentViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val database: Database
) :
    ViewModel() {

    private val authRepo = AuthRepo(authApi)
    var isLoading = MutableLiveData<Boolean>(false)

    fun onLogout(response: (Boolean) -> Unit) {

        viewModelScope.launch {
            isLoading.postValue(true)

            val loggedInUser = database.userDao().getUser()!!

            val isLoggedOut = authRepo.logout(loggedInUser)
            if (isLoggedOut)
                database.userDao().deleteUser(loggedInUser)
            response(isLoggedOut)

            isLoading.postValue(false)
        }

    }
}