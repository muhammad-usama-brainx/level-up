package com.example.levelup.fragments

import com.example.levelup.adapters.Notification_Recycler_Adapter
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.levelup.MainActivity
import com.example.levelup.R
import com.example.levelup.R.id
import com.example.levelup.models.Notification


class NotificationFragment : Fragment() {

    private lateinit var context: Context
    private lateinit var notifications: MutableList<Notification>
    private lateinit var notificationAdapter: Notification_Recycler_Adapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notifications = mutableListOf()
        notifications.add(
            Notification("Usama", "Software Engineer", "Meeting",
                "You have meeting with Faizan bhai at 3 Pm", "2:36 AM")
        )


        notificationAdapter = Notification_Recycler_Adapter(notifications, context)
        val recyclerView: RecyclerView = view.findViewById(R.id.notificationRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = notificationAdapter

    }
}