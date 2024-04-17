package com.vincent.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.vincent.myapplication.R
import com.vincent.myapplication.databinding.ActivityMainBinding
import com.vincent.myapplication.model.User

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private lateinit var navController: NavController

    companion object{
        var user: User? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
        bind.bottomNav.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this, navController, bind.drawerLayout)
        NavigationUI.setupWithNavController(bind.navView, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, bind.drawerLayout)
                || super.onSupportNavigateUp()
    }

}