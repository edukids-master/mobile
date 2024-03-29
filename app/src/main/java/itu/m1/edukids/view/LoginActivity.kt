package itu.m1.edukids.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import itu.m1.edukids.R
import itu.m1.edukids.controller.UserController
import itu.m1.edukids.databinding.ActivityLoginBinding
import itu.m1.edukids.model.User

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var loginViewModel: UserController

    private lateinit var loginText: EditText
    private lateinit var passwordText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_login)

        loginText = binding.loginText
        passwordText = binding.passwordText

        loginViewModel = UserController()
        binding.viewModel = loginViewModel

        binding.connexionBtn.setOnClickListener {
            connexion()
        }
    }

    private fun connexion() {
        loginViewModel.connexion(User(loginText.text.toString(), passwordText.text.toString()))
        loginViewModel.error.observe(this, Observer {
            Snackbar.make(
            this,
            binding.root,
            "$it",
            Snackbar.LENGTH_SHORT
        ).show() })
//        loginViewModel.error?.value?.let {
//
//        }
    }
}