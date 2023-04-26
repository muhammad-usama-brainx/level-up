package com.example.levelup.viewModels.notification

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelup.data.Database
import com.example.levelup.data.notification.Announcement
import com.example.levelup.repo.NotificationRepo
import kotlinx.coroutines.launch

@SuppressLint("NotifyDataSetChanged")
class NotificationFragmentViewModel(
    context: Context,
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