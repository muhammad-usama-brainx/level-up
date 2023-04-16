package com.example.levelup

import com.example.levelup.fragments.HomeFragment
import com.example.levelup.fragments.NotificationFragment
import com.example.levelup.fragments.SettingsFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
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


        //showing home fragment by default
        replaceFragment(HomeFragment())

        //Bottom Navigation click listener
        binding.bottomNavigationBar.setOnItemSelectedListener {
            //getting fragment to display by ID
            val fragmentToShow = mainViewModel.getFragmentFromMenuId(it)
            replaceFragment(fragmentToShow)
          true
     }



    }
    private fun replaceFragment(fragment: Fragment)
    {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameContainer,fragment)
        transaction.commit()
    }
}