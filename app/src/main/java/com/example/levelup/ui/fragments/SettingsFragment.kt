package com.example.levelup.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.levelup.R
import com.example.levelup.ui.activities.LoginActivity
import com.example.levelup.databinding.FragmentSettingsBinding
import com.example.levelup.ui.viewModels.SettingsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private val viewModel: SettingsFragmentViewModel by viewModels()
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBarListener()

        binding.logoutCardView.setOnClickListener {

            viewModel.onLogout { isLoggedOut ->
                if (isLoggedOut) {
                    Intent(requireContext(), LoginActivity::class.java).apply {
                        startActivity(this)
                        requireActivity().finish()
                    }
                }
            }
        }
    }

    private fun progressBarListener() {
        //Progressbar
        viewModel.isLoading.observe(requireActivity(), Observer { isBusy ->

            if (isBusy) {
                binding.progressBar2.visibility = View.VISIBLE
                binding.scrollView.visibility = View.INVISIBLE
            } else {
                binding.progressBar2.visibility = View.INVISIBLE
                binding.scrollView.visibility = View.VISIBLE
            }
        })
    }

}