package com.dudegenuine.manualbook.ui.activity

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.dudegenuine.domain.Chapter
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.databinding.ActivityMainBinding
import com.dudegenuine.manualbook.ui.activity.MainBinding.setBottomAppBar

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    private val TAG: String = javaClass.simpleName
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment).apply {
            appBarConfiguration = AppBarConfiguration(this.graph)

            addOnDestinationChangedListener(this@MainActivity)
        }
    } // setupActionBarWithNavController(navController, appBarConfiguration)

    override fun onNavigateUp(): Boolean =
        navController.navigateUp(appBarConfiguration) || super.onNavigateUp()

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle? ) {
        when (destination.id) {
            R.id.homeFragment ->
                setBottomAppBar(binding, R.drawable.ic_baseline_search_24, getString(R.string.app_name))  // R.menu.bottom_app_bar_menu_detail,
            R.id.detailFragment ->
                setBottomAppBar(binding, R.drawable.ic_baseline_volume_up_24, (arguments?.getSerializable("chapter") as Chapter?)
                    ?.nameSimple ?: getString(R.string.app_name)
                )
        }
    }
}