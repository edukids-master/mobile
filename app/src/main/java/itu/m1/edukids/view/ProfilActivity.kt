package itu.m1.edukids.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import itu.m1.edukids.MainActivity
import itu.m1.edukids.R
import itu.m1.edukids.controller.UserController
import itu.m1.edukids.model.User

class ProfilActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Profil")
        setContentView(R.layout.activity_profil)
        init()
    }

    private var control: UserController? = null
    private lateinit var nomTxt : TextView
    fun init(){
        control = UserController.getInstance(this)
        var user = UserController.localAccess.recupDernier()
        nomTxt = findViewById(R.id.text_nom)
        nomTxt.text = user?.nom + " " + user?.prenom

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.video_top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_close -> {
                finish()
            }
        }

        return true
    }
}