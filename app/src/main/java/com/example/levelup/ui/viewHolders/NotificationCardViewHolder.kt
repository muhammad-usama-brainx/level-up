package com.example.levelup.ui.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelup.R

class NotificationCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var name: TextView = view.findViewById(R.id.name)
    var role: TextView = view.findViewById(R.id.role)
    var title: TextView = view.findViewById(R.id.title)
    var message: TextView = view.findViewById(R.id.message)
    var time: TextView = view.findViewById(R.id.time)
}