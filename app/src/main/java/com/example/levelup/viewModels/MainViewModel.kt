package com.example.levelup.viewModels

import android.content.Context
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.levelup.R
import com.example.levelup.fragments.HomeFragment
import com.example.levelup.fragments.NotificationFragment
import com.example.levelup.fragments.SettingsFragment

class MainViewModel : ViewModel() {


    fun getFragmentFromMenuId(item: MenuItem) : Fragment
    {
        var fragment: Fragment = when(item.itemId) {
            R.id.home -> HomeFragment()
            R.id.notification -> NotificationFragment()
            R.id.settings -> SettingsFragment()
            else -> HomeFragment()
        }
        return  fragment
    }


}