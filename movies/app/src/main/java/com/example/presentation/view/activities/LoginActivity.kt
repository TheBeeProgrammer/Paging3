package com.example.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.movies.R
import com.example.movies.databinding.ActivityLoginBinding
import com.example.presentation.viewmodel.LoginViewModel
import java.time.Duration


class LoginActivity : AppCompatActivity() {

    private var binding: ActivityLoginBinding? = null
    private lateinit var viewmodel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding?.loginViewModel = viewmodel
        binding?.lifecycleOwner = this

        setContentView(binding?.root)

        viewmodel.eventIsSuccess.observe(this, { isSuccess ->
            if (isSuccess) navigateToMovieFragment() else Toast.makeText(
                this,
                getString(R.string.login_error),
                Toast.LENGTH_SHORT
            ).show()
        })
    }


    private fun navigateToMovieFragment() {
        val intent = Intent(this, MovieActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}