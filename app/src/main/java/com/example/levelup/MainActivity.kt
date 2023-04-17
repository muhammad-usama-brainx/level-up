package com.example.levelup

import SelectedNavItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.levelup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var selectedNavItem: SelectedNavItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        selectedNavItem = SelectedNavItem.Home
        updateActiveNavItem()
        replaceFragment(HomeFragment())

        binding.bottomNavigationBar.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.home -> {
                    selectedNavItem = SelectedNavItem.Home
                    updateActiveNavItem()
                    replaceFragment(HomeFragment())
                }
                R.id.notification -> {
                    selectedNavItem = SelectedNavItem.Notification
                    updateActiveNavItem()
                    replaceFragment(NotificationFragment())
                }
                R.id.settings -> {
                    selectedNavItem = SelectedNavItem.Settings
                    updateActiveNavItem()
                    replaceFragment(SettingsFragment())
                }
            }
            true
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameContainer, fragment)
        transaction.commit()
    }

    private fun updateActiveNavItem() {
        binding.homeBorder.visibility = View.INVISIBLE
        binding.notificationBorder.visibility = View.INVISIBLE
        binding.settingsBorder.visibility = View.INVISIBLE

        when (selectedNavItem) {
            SelectedNavItem.Home -> {
                binding.homeBorder.visibility = View.VISIBLE
            }
            SelectedNavItem.Notification -> {
                binding.notificationBorder.visibility = View.VISIBLE
            }
            else -> {
                binding.settingsBorder.visibility = View.VISIBLE
            }
        }
    }
}