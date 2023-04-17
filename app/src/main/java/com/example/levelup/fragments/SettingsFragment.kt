package com.example.levelup.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.example.levelup.R

class SettingsFragment : Fragment() {

    private lateinit var profileCardView: CardView
    private lateinit var changePasswordCardView: CardView
    private lateinit var coachingCardView: CardView
    private lateinit var contactCardView: CardView
    private lateinit var privacyCardView: CardView
    private lateinit var faqCardView: CardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileCardView = view.findViewById(R.id.profileCardView)
        changePasswordCardView = view.findViewById(R.id.changePasswordCardView)
        coachingCardView = view.findViewById(R.id.coachingPlanCardView)
        contactCardView = view.findViewById(R.id.contactUsCardView)
        privacyCardView = view.findViewById(R.id.privacyCardView)
        faqCardView = view.findViewById(R.id.faqCardView)

    }

}