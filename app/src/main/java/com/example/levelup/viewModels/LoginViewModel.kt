package com.example.levelup.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel

class LoginViewModel() : ViewModel() {

    fun isValidInput(email: String, password: String): Boolean {
        return true
    }
}