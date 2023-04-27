package com.example.levelup.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.levelup.R
import com.example.levelup.ui.viewModels.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var nameTextView: TextView
    private lateinit var quoteTextView: TextView
    private lateinit var quoteWriterTextView: TextView
    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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