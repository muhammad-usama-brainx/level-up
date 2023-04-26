package com.example.levelup.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.levelup.R
import com.example.levelup.api.AuthApi
import com.example.levelup.api.RetrofitHelper
import com.example.levelup.data.Database
import com.example.levelup.viewModels.home.HomeFragmentViewModel
import com.example.levelup.viewModels.home.HomeFragmentViewModelFactory
import retrofit2.Retrofit


class HomeFragment : Fragment() {

    private lateinit var nameTextView: TextView
    private lateinit var quoteTextView: TextView
    private lateinit var quoteWriterTextView: TextView

    private lateinit var viewModel: HomeFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val authApi = RetrofitHelper.getInstance().create(AuthApi::class.java)
        val database = Room.databaseBuilder(
            requireContext().applicationContext,
            Database::class.java,
            "database"
        )
            .fallbackToDestructiveMigration().build()

        viewModel = ViewModelProvider(
            this,
            HomeFragmentViewModelFactory(authApi, database)
        )[HomeFragmentViewModel::class.java]

        nameTextView = view.findViewById(R.id.nameTextView)
        quoteTextView = view.findViewById(R.id.quoteTextView)
        quoteWriterTextView = view.findViewById(R.id.quoteWriterTextView)

        viewModel.name.observe(requireActivity(), Observer {
            nameTextView.text = it
        })
        quoteTextView.text = viewModel.quote()
        quoteWriterTextView.text = viewModel.quoteWriter()
    }

}