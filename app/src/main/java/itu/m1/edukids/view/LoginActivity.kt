package itu.m1.edukids.view

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import itu.m1.edukids.CustomLoading
import itu.m1.edukids.MainActivity
import itu.m1.edukids.R
import itu.m1.edukids.controller.UserController
import itu.m1.edukids.databinding.ActivityLoginBinding
import itu.m1.edukids.model.Notification
import itu.m1.edukids.model.User
import itu.m1.edukids.service.NetworkService
import itu.m1.edukids.service.NetworkService.checkCurrentNetworkStatus

class LoginActivity : MainActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: UserController

    private lateinit var loginText: EditText
    private lateinit var passwordText: EditText

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_login)

        NetworkService.checkInternetConnectionOnBackground()
        Notification.createNotificationChannel(this)

        loginText = binding.loginText
        passwordText = binding.passwordText

        loginViewModel = UserController()
        binding.viewModel = loginViewModel

        loginViewModel.error.observe(this, Observer {
            Snackbar.make(
                this,
                binding.root,
                "$it",
                Snackbar.LENGTH_SHORT
            ).show()
        })

        binding.connexionBtn.setOnClickListener {
            connexion()
        }
    }

    private fun connexion() {
        var loading = CustomLoading(this)
        loading.startLoading()
        loginViewModel.connexion(this,User(loginText.text.toString(), passwordText.text.toString())) {
                loading.hideLoading()
        }

    }
}