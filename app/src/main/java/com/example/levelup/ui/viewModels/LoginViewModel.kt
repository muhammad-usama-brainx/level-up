package com.example.levelup.ui.viewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelup.data.remote.api.AuthApi
import com.example.levelup.data.Database
import com.example.levelup.data.models.user.User
import com.example.levelup.data.repo.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val database: Database
) : ViewModel() {


    private val authRepo: AuthRepo = AuthRepo(authApi)

    private val userLiveData = MutableLiveData<User>()
    val user: LiveData<User>
        get() = userLiveData


    var isLoading = MutableLiveData<Boolean>(false)
    var error = MutableLiveData<String?>(null)

    fun tryAutoLogin() {
        viewModelScope.launch {
            val user = database.userDao().getUser()
            if (user != null)
                userLiveData.postValue(user)
        }
    }


    fun login(email: String, password: String) {


        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            val user = authRepo.login(email, password)
            if (user == null)  //Fail to login
                error.postValue("Login failed. Please check credentials")
            else {
                println(user.toString())
                database.userDao().insertUser(user)
                userLiveData.postValue(user)
            }
            isLoading.postValue(false)
        }


    }

    fun isValidInput(email: String, password: String): Boolean {
        if (!email.contains("@") || password.length < 6)
            return false
        return true
    }
}