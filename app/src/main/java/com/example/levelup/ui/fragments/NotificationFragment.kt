package com.example.levelup.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.levelup.R
import com.example.levelup.ui.adapters.NotificationRecyclerAdapter
import com.example.levelup.databinding.FragmentNotificationBinding
import com.example.levelup.ui.viewModels.home.NotificationFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NotificationFragment : Fragment() {

    private lateinit var context: Context
    private val viewModel: NotificationFragmentViewModel by viewModels()
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