package com.example.levelup.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.levelup.MainActivity
import com.example.levelup.databinding.ActivityLoginBinding
import com.example.levelup.viewModels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            val isValidInput = loginViewModel.isValidInput(email, password)

            if (isValidInput) {
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}