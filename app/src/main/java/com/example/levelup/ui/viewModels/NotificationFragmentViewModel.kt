package com.example.levelup.ui.viewModels

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelup.data.Database
import com.example.levelup.data.models.notification.Announcement
import com.example.levelup.data.repo.NotificationRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("NotifyDataSetChanged")
@HiltViewModel
class NotificationFragmentViewModel @Inject constructor(
    private val notificationRepo: NotificationRepo,
    private val database: Database,
) : ViewModel() {

    var announcements = MutableLiveData<MutableList<Announcement>>(mutableListOf())
    val isLoading = MutableLiveData<Boolean>()


    init {
        viewModelScope.launch {
            isLoading.postValue(true)

            val loggedInUser = database.userDao().getUser()!!

            val fetchedNotifications = notificationRepo.getNotifications(loggedInUser)

            if (fetchedNotifications != null) {
                announcements.value?.clear()
                announcements.value?.addAll(fetchedNotifications)
                announcements.notifyObserver()
            }
            isLoading.postValue(false)
        }

    }


    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}