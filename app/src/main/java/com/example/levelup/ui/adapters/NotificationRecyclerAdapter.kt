package com.example.levelup.ui.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelup.R
import com.example.levelup.data.models.notification.Announcement
import com.example.levelup.ui.viewHolders.NotificationCardViewHolder


class NotificationRecyclerAdapter(
    private val notificationsList: MutableList<Announcement>,
) : RecyclerView.Adapter<NotificationCardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationCardViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.notification_card, parent, false)
        return NotificationCardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notificationsList.size
    }

    override fun onBindViewHolder(holder: NotificationCardViewHolder, position: Int) {
        holder.name.text = notificationsList[position].name
        holder.role.text = notificationsList[position].role
        holder.title.text = notificationsList[position].title
        holder.message.text = notificationsList[position].message
        holder.time.text = notificationsList[position].time
    }


}