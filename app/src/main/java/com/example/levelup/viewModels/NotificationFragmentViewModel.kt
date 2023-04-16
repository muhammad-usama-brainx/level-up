package com.example.levelup.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.levelup.adapters.Notification_Recycler_Adapter
import com.example.levelup.models.Notification

class NotificationFragmentViewModel(private val context: Context) : ViewModel() {

    private val notifications = mutableListOf<Notification>(
        Notification(
            "Usama", "Software Engineer", "Meeting",
            "You have meeting with Faizan bhai at 3 Pm", "2:36 AM"
        )
    )

    val notificationAdapter = Notification_Recycler_Adapter(notifications, context)
}