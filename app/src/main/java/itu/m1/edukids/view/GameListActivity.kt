package itu.m1.edukids.view

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import itu.m1.edukids.R
import itu.m1.edukids.databinding.ActivityGameListBinding
import com.imagekit.android.ImageKit;
import com.imagekit.android.entity.TransformationPosition
import itu.m1.edukids.MainActivity

class GameListActivity : MainActivity() {

    private lateinit var binding: ActivityGameListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_game_list)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        ImageKit.init(
            context = this.applicationContext,
            publicKey = "public_b7aTnJoygZr6YrBzuWxinJHIn8E=",
            urlEndpoint = "https://ik.imagekit.io/g3gggequm",
            transformationPosition = TransformationPosition.PATH,
            authenticationEndpoint = "http://www.yourserver.com/auth"
        )

    }
}