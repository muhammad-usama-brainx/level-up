package com.example.levelup.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.levelup.ui.MainActivity
import com.example.levelup.MyApp
import com.example.levelup.data.remote.api.AuthApi
import com.example.levelup.data.remote.api.RetrofitHelper
import com.example.levelup.databinding.ActivityLoginBinding
import com.example.levelup.ui.viewModels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressBarListener()
        successLoginListener()
        failLoginListener()

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val isValidInput = loginViewModel.isValidInput(email, password)

            if (isValidInput) {
                loginViewModel.login(email, password)
            } else {
                Toast.makeText(this, "Invalid inputs", Toast.LENGTH_SHORT).show()
            }
        }


        //Autologin
        loginViewModel.tryAutoLogin()

    }

    private fun progressBarListener() {
        //Progressbar
        loginViewModel.isLoading.observe(this, Observer { isBusy ->
            println(isBusy)
            if (isBusy) {
                binding.progressBar.visibility = View.VISIBLE
                binding.mainView.visibility = View.INVISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
                binding.mainView.visibility = View.VISIBLE
            }
        })
    }

    private fun successLoginListener() {
        //user logged in successfully
        loginViewModel.user.observe(this, Observer { user ->
            if (user != null) {
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        })
    }

    private fun failLoginListener() {
        //has error while logging in
        loginViewModel.error.observe(this, Observer { error ->
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        })
    }
}