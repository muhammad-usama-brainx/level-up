package com.example.levelup.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelup.data.remote.api.AuthApi
import com.example.levelup.data.Database
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val database: Database
) :
    ViewModel() {

    var name = MutableLiveData<String>("Unknown")
    private var quote = "\"You can make money or you can make excuses. Which do you prefer?\""
    private var quoteWriter = "Usama Javed"

    init {
        viewModelScope.launch {
            name.postValue(database.userDao().getUser()!!.name)
        }
    }

    fun quote(): String = quote
    fun quoteWriter(): String = quoteWriter


}