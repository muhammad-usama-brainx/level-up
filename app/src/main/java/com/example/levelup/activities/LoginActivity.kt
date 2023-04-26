package com.example.levelup.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.levelup.MainActivity
import com.example.levelup.MyApp
import com.example.levelup.api.AuthApi
import com.example.levelup.api.RetrofitHelper
import com.example.levelup.data.Database
import com.example.levelup.databinding.ActivityLoginBinding
import com.example.levelup.viewModels.login.LoginViewModel
import com.example.levelup.viewModels.login.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val authApi = RetrofitHelper.getInstance().create(AuthApi::class.java)
        val myApp = applicationContext as MyApp
        val database = myApp.databaseInstance()

        loginViewModel =
            ViewModelProvider(
                this,
                LoginViewModelFactory(authApi, database)
            )[LoginViewModel::class.java]


        //Autologin
        loginViewModel.tryAutoLogin()


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