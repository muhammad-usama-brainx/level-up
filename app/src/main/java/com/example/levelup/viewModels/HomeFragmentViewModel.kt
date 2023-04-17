package com.example.levelup.viewModels

import androidx.lifecycle.ViewModel

class HomeFragmentViewModel : ViewModel() {
    private var name = "Usama Javed"
    private var quote = "\"You can make money or you can make excuses. Which do you prefer?\""
    private var quoteWriter = "Usama Javed"


    fun name(): String = name
    fun quote(): String = quote
    fun quoteWriter(): String = quoteWriter


}