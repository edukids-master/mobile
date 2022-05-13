package itu.m1.edukids.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import itu.m1.edukids.R
import java.util.*
import kotlin.concurrent.schedule


class SplashScreenActivity : AppCompatActivity() {
    private lateinit var splash: ImageView
    private lateinit var fade_in_Anim: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen3)

        splash = findViewById(R.id.splash)
        fade_in_Anim = AnimationUtils.loadAnimation(this, R.anim.fade_in_vert);

        splash.startAnimation(fade_in_Anim)
        splash.visibility = View.VISIBLE

        val intent = Intent(this, LoginActivity::class.java)
        Timer("SettingUp", false).schedule(3000) {
            startActivity(intent)
        }

    }
}