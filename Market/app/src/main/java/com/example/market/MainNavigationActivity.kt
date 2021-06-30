package com.example.market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main_navigation.*

class MainNavigationActivity : AppCompatActivity() {

    private  lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation)
        supportActionBar?.hide()

        val navController = findNavController(R.id.mainFragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.productFragment,
            R.id.createProductFragment,
        ))

        mainBottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return  navController.navigateUp() || super.onSupportNavigateUp()
    }

}