package com.dudegenuine.manualbook.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.dudegenuine.manualbook.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val TAG: String = javaClass.simpleName
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController(R.id.nav_host_fragment).apply {
            appBarConfiguration = AppBarConfiguration(this.graph)
        } // setupActionBarWithNavController(navController, navConfiguration)
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onNavigateUp()
    }

}