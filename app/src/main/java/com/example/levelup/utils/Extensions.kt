package com.example.levelup.utils

import androidx.lifecycle.MutableLiveData


fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}