package com.example.levelup

import Fragments.HomeFragment
import Fragments.NotificationFragment
import Fragments.SettingsFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.levelup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        replaceFragment(HomeFragment())

        binding.bottomNavigationBar.setOnItemSelectedListener {

         when(it.itemId)
         {
             R.id.home -> replaceFragment(HomeFragment())
             R.id.notification -> replaceFragment(NotificationFragment())
             R.id.settings -> replaceFragment(SettingsFragment())
         }
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