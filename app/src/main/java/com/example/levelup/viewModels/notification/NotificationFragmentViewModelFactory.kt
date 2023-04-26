package com.example.levelup.viewModels.notification

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelup.data.Database
import com.example.levelup.repo.NotificationRepo

class NotificationFragmentViewModelFactory(
    private val context: Context, private val notificationRepo: NotificationRepo,
    private val database: Database,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotificationFragmentViewModel(context, notificationRepo, database) as T
    }
}