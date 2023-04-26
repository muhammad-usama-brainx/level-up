package com.example.levelup.fragments

import android.annotation.SuppressLint
import android.app.Notification
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.levelup.R
import com.example.levelup.adapters.NotificationRecyclerAdapter
import com.example.levelup.api.NotificationApi
import com.example.levelup.api.RetrofitHelper
import com.example.levelup.data.Database
import com.example.levelup.databinding.FragmentNotificationBinding
import com.example.levelup.repo.NotificationRepo
import com.example.levelup.viewModels.notification.NotificationFragmentViewModel
import com.example.levelup.viewModels.notification.NotificationFragmentViewModelFactory


class NotificationFragment : Fragment() {

    private lateinit var context: Context
    private lateinit var viewModel: NotificationFragmentViewModel
    private lateinit var binding: FragmentNotificationBinding
    private lateinit var announcementAdapter: NotificationRecyclerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val notificationApi = RetrofitHelper.getInstance().create(NotificationApi::class.java)
        val notificationRepo = NotificationRepo(notificationApi)
        val database = Room.databaseBuilder(
            requireContext().applicationContext, Database::class.java, "database"
        ).fallbackToDestructiveMigration().build()


        viewModel =
            ViewModelProvider(
                this,
                NotificationFragmentViewModelFactory(requireContext(), notificationRepo, database)
            )[NotificationFragmentViewModel::class.java]


        announcementAdapter =
            NotificationRecyclerAdapter(viewModel.announcements.value!!, requireContext())

        viewModel.announcements.observe(requireActivity(), Observer {
            println(viewModel.announcements.value.toString())
            announcementAdapter.notifyDataSetChanged()
        })


        progressBarListener()

        val recyclerView: RecyclerView = view.findViewById(R.id.notificationRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = announcementAdapter


    }

    private fun progressBarListener() {
        viewModel.isLoading.observe(requireActivity(), Observer { isBusy ->
            if (isBusy) {
                binding.progressBar3.visibility = View.VISIBLE
                binding.notificationRecyclerView.visibility = View.INVISIBLE
            } else {
                binding.progressBar3.visibility = View.INVISIBLE
                binding.notificationRecyclerView.visibility = View.VISIBLE
            }

        })
    }
}