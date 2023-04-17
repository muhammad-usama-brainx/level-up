package com.example.levelup


import SelectedNavItem
import com.example.levelup.fragments.HomeFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.levelup.databinding.ActivityMainBinding
import com.example.levelup.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //Getting instance of view model
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        updateActiveNavItem()
        //showing home fragment by default
        replaceFragment(HomeFragment())

        //Bottom Navigation click listener
        binding.bottomNavigationBar.setOnItemSelectedListener {
        
            //getting fragment to display by ID
            val fragmentToShow = mainViewModel.getFragmentFromMenuId(it)
            replaceFragment(fragmentToShow)
            updateActiveNavItem()

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