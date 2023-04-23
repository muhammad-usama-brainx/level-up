package com.example.levelup.viewModels.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.levelup.api.AuthApi
import com.example.levelup.repo.AuthRepo

class HomeFragmentViewModel(private val authApi: AuthApi, private val context: Context) :
    ViewModel() {
    private var name = AuthRepo.getInstance(authApi, context).user.value!!.name
    private var quote = "\"You can make money or you can make excuses. Which do you prefer?\""
    private var quoteWriter = "Usama Javed"


    fun name(): String = name
    fun quote(): String = quote
    fun quoteWriter(): String = quoteWriter


}