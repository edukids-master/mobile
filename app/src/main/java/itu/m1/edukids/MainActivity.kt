package itu.m1.edukids

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import itu.m1.edukids.controller.UserController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        UserController()
    }
}