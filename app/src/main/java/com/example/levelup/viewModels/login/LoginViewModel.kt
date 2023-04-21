package com.example.levelup.viewModels.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelup.api.AuthApi
import com.example.levelup.models.User
import com.example.levelup.repo.AuthRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val authApi: AuthApi) : ViewModel() {


    private val authRepo: AuthRepo = AuthRepo.getInstance(authApi)

    var isLoading = MutableLiveData<Boolean>(false)
    var error = MutableLiveData<String?>(null)
    val user: LiveData<User>
        get() = authRepo.user


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
        return true
    }
}