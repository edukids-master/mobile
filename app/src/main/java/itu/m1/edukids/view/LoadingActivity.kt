package itu.m1.edukids.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import itu.m1.edukids.R
import java.util.*
import kotlin.concurrent.schedule


class LoadingActivity : AppCompatActivity() {

    private lateinit var fade_in_Anim_bleu: Animation
    private lateinit var fade_in_Anim_vert: Animation
    private lateinit var fade_in_Anim_rouge: Animation

    private lateinit var piedbleu: ImageView
    private lateinit var piedvert: ImageView
    private lateinit var piedrouge: ImageView

    private lateinit var chargement: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)




        fade_in_Anim_bleu = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fade_in_Anim_vert = AnimationUtils.loadAnimation(this, R.anim.fade_in_vert);
        fade_in_Anim_rouge = AnimationUtils.loadAnimation(this, R.anim.fade_in_rouge);

        piedbleu = findViewById(R.id.piedbleu);
        piedvert = findViewById(R.id.piedvert);
        piedrouge = findViewById(R.id.piedrouge);
        chargement = findViewById(R.id.chargement);


        piedbleu.visibility = View.INVISIBLE
        piedvert.visibility = View.INVISIBLE
        piedrouge.visibility = View.INVISIBLE

        val systemImage: MutableList<ImageView> = mutableListOf(piedbleu,piedvert,piedrouge)

        val sudoers: List<ImageView> = systemImage


            fun animation(){
                sudoers[0].startAnimation(fade_in_Anim_bleu);
                if(sudoers[0].animation.hasStarted() === false){
                    sudoers[0].visibility = View.VISIBLE
                    sudoers[1].startAnimation(fade_in_Anim_vert);
                    sudoers[1].visibility = View.VISIBLE
                    if(sudoers[1].animation.hasStarted() === false){
                        sudoers[2].startAnimation(fade_in_Anim_rouge);
                        sudoers[2].visibility = View.VISIBLE
                    }
                }
            }

        animation()
    }
}