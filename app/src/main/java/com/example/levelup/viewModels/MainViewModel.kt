package com.example.levelup.viewModels

import SelectedNavItem
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

    var selectedNavItem: SelectedNavItem = SelectedNavItem.Home

    fun getFragmentFromMenuId(item: MenuItem) : Fragment
    {
        val fragment: Fragment = when(item.itemId) {
            R.id.home -> {
                selectedNavItem = SelectedNavItem.Home
                HomeFragment()
            }
            R.id.notification -> {
                selectedNavItem = SelectedNavItem.Notification
                NotificationFragment()
            }
            R.id.settings -> {
                selectedNavItem = SelectedNavItem.Settings
                SettingsFragment()
            }
            else -> HomeFragment()
        }
        return  fragment
    }


}