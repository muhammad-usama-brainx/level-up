package com.example.levelup.ui


import SelectedNavItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.levelup.R
import com.example.levelup.ui.adapters.ViewPagerAdapter
import com.example.levelup.databinding.ActivityMainBinding
import com.example.levelup.ui.viewModels.MainViewModel
import com.google.android.material.badge.BadgeDrawable

class MainActivity : AppCompatActivity() {

    private lateinit var badgeDrawable: BadgeDrawable
    private lateinit var notificationMenuItem: MenuItem
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //Getting instance of view model
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]


        //Setting badge for notification
        notificationMenuItem = binding.bottomNavigationBar.menu.findItem(R.id.notification)
        badgeDrawable = binding.bottomNavigationBar.getOrCreateBadge(notificationMenuItem.itemId)
        badgeDrawable.isVisible = true
        badgeDrawable.backgroundColor = ContextCompat.getColor(this, R.color.blue_500)
        badgeDrawable.badgeGravity = BadgeDrawable.TOP_END

        viewPagerAdapter = ViewPagerAdapter(this, mainViewModel.fragments)
        binding.viewPager.adapter = viewPagerAdapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Called when a new page has been selected
                when (position) {
                    0 -> {
                        mainViewModel.selectedNavItem = SelectedNavItem.Home
                        binding.bottomNavigationBar.selectedItemId = R.id.home
                        updateActiveNavItem()
                    }
                    1 -> {
                        mainViewModel.selectedNavItem = SelectedNavItem.Notification
                        binding.bottomNavigationBar.selectedItemId = R.id.notification
                        updateActiveNavItem()
                    }
                    2 -> {
                        mainViewModel.selectedNavItem = SelectedNavItem.Settings
                        binding.bottomNavigationBar.selectedItemId = R.id.settings
                        updateActiveNavItem()
                    }
                }
            }
        })


        binding.bottomNavigationBar.setOnItemSelectedListener {
            binding.viewPager.currentItem = mainViewModel.getPositionFromMenuItem(it)
            true
        }
    }

    private fun updateActiveNavItem() {
        binding.homeBorder.visibility = View.INVISIBLE
        binding.notificationBorder.visibility = View.INVISIBLE
        binding.settingsBorder.visibility = View.INVISIBLE

        when (mainViewModel.selectedNavItem) {
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