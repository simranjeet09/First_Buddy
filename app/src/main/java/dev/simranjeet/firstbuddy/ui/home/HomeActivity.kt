package dev.simranjeet.firstbuddy.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.simranjeet.firstbuddy.R
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein


class HomeActivity : AppCompatActivity(), KodeinAware{
    override val kodein by kodein()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
       val navController: NavController =
            Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)
        setupWithNavController(bottomNavigationView, navController)


    }

}