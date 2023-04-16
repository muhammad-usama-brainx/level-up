package com.example.levelup.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.levelup.R
import com.example.levelup.viewModels.NotificationFragmentViewModel


class NotificationFragment : Fragment() {

    private lateinit var context: Context
    private lateinit var viewModel: NotificationFragmentViewModel
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


        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return NotificationFragmentViewModel(context) as T
            }
        }
        viewModel =
            ViewModelProvider(this, factory)[NotificationFragmentViewModel(context)::class.java]

        val recyclerView: RecyclerView = view.findViewById(R.id.notificationRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModel.notificationAdapter

    }
}