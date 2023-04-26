package com.example.levelup.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.levelup.MyApp
import com.example.levelup.R
import com.example.levelup.activities.LoginActivity
import com.example.levelup.api.AuthApi
import com.example.levelup.api.RetrofitHelper
import com.example.levelup.data.Database
import com.example.levelup.databinding.FragmentSettingsBinding
import com.example.levelup.viewModels.settings.SettingsFragmentViewModel
import com.example.levelup.viewModels.settings.SettingsFragmentViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.create

class SettingsFragment : Fragment() {

    private lateinit var profileCardView: CardView
    private lateinit var changePasswordCardView: CardView
    private lateinit var coachingCardView: CardView
    private lateinit var contactCardView: CardView
    private lateinit var privacyCardView: CardView
    private lateinit var faqCardView: CardView
    private lateinit var logoutCardView: CardView

    private lateinit var viewModel: SettingsFragmentViewModel
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

        val authApi = RetrofitHelper.getInstance().create(AuthApi::class.java)
        val myApp = requireContext().applicationContext as MyApp
        val database = myApp.databaseInstance()

        viewModel = ViewModelProvider(
            this,
            SettingsFragmentViewModelFactory(authApi, database)
        )[SettingsFragmentViewModel::class.java]


        profileCardView = view.findViewById(R.id.profileCardView)
        changePasswordCardView = view.findViewById(R.id.changePasswordCardView)
        coachingCardView = view.findViewById(R.id.coachingPlanCardView)
        contactCardView = view.findViewById(R.id.contactUsCardView)
        privacyCardView = view.findViewById(R.id.privacyCardView)
        faqCardView = view.findViewById(R.id.faqCardView)
        logoutCardView = view.findViewById(R.id.logoutCardView)


        progressBarListener()

        logoutCardView.setOnClickListener {

            MainScope().launch {
                val isLoggedOut = viewModel.onLogout()

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