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
    val fragments = mutableListOf<Fragment>(
        HomeFragment(), NotificationFragment(), SettingsFragment()
    )

    fun getPositionFromMenuItem(item: MenuItem): Int {
        val position: Int = when (item.itemId) {
            R.id.home -> {
                selectedNavItem = SelectedNavItem.Home
                0
            }
            R.id.notification -> {
                selectedNavItem = SelectedNavItem.Notification
                1
            }
            R.id.settings -> {
                selectedNavItem = SelectedNavItem.Settings
                2
            }
            else -> 0
        }
        return position
    }


}