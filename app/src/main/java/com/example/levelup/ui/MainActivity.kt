package com.example.levelup.ui


import com.example.levelup.utils.SelectedNavItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.levelup.R
import com.example.levelup.ui.adapters.ViewPagerAdapter
import com.example.levelup.databinding.ActivityMainBinding
import com.example.levelup.ui.fragments.HomeFragment
import com.example.levelup.ui.fragments.NotificationFragment
import com.example.levelup.ui.fragments.SettingsFragment
import com.google.android.material.badge.BadgeDrawable
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var badgeDrawable: BadgeDrawable
    private lateinit var notificationMenuItem: MenuItem
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var fragments: MutableList<Fragment>
    private lateinit var selectedNavItem: SelectedNavItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Setting badge for notification
        notificationMenuItem = binding.bottomNavigationBar.menu.findItem(R.id.notification)
        badgeDrawable = binding.bottomNavigationBar.getOrCreateBadge(notificationMenuItem.itemId)
        badgeDrawable.isVisible = true
        badgeDrawable.backgroundColor = ContextCompat.getColor(this, R.color.blue_500)
        badgeDrawable.badgeGravity = BadgeDrawable.TOP_END


        selectedNavItem = SelectedNavItem.Home
        fragments = mutableListOf<Fragment>(
            HomeFragment(), NotificationFragment(), SettingsFragment()
        )
        viewPagerAdapter = ViewPagerAdapter(this, fragments)
        binding.viewPager.adapter = viewPagerAdapter

        binding.bottomNavigationBar.setOnItemSelectedListener {
            binding.viewPager.currentItem = getPositionFromMenuItem(it)
            true
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Called when a new page has been selected
                when (position) {
                    0 -> {
                        selectedNavItem = SelectedNavItem.Home
                        binding.bottomNavigationBar.selectedItemId = R.id.home
                        updateActiveNavItem()
                    }
                    1 -> {
                        selectedNavItem = SelectedNavItem.Notification
                        binding.bottomNavigationBar.selectedItemId = R.id.notification
                        updateActiveNavItem()
                    }
                    2 -> {
                        selectedNavItem = SelectedNavItem.Settings
                        binding.bottomNavigationBar.selectedItemId = R.id.settings
                        updateActiveNavItem()
                    }
                }
            }
        })


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

    private fun getPositionFromMenuItem(item: MenuItem): Int {
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