package com.dudegenuine.manualbook.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.dudegenuine.domain.Chapter
import com.dudegenuine.manualbook.NavGraphHomeFeatureDirections
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.databinding.ActivityMainBinding
import com.dudegenuine.manualbook.ui.activity.MainBinding.bindFragmentTransition
import com.dudegenuine.manualbook.ui.activity.MainBinding.setBottomAppBar
import com.dudegenuine.manualbook.ui.fragment.home.FragmentHomeDirections

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    private val TAG: String = javaClass.simpleName
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bindFragmentTransition()

        navController = findNavController(R.id.nav_host_fragment).apply { // setupActionBarWithNavController(navController, appBarConfiguration)
            appBarConfiguration = AppBarConfiguration(this.graph)

            addOnDestinationChangedListener(this@MainActivity)
        }
    }

    override fun onNavigateUp(): Boolean =
        navController.navigateUp(appBarConfiguration) || super.onNavigateUp()

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle? ) {
        when (destination.id) {

            R.id.homeFragment -> setBottomAppBar(binding, R.drawable.ic_baseline_search_24, getString(R.string.app_name)) {
                navController.navigate( NavGraphHomeFeatureDirections.actionGlobalToSearch() )
            }

            R.id.detailFragment -> setBottomAppBar(binding, R.drawable.ic_baseline_volume_up_24,
                (arguments?.getSerializable("chapter") as Chapter?)?.nameSimple ?: getString(R.string.app_name)){

                Log.d(TAG, "onDestinationChanged: detail fab triggered")
                // TODO: 25/06/21 create baseActivity that implements snack bar and navigation event
            }
        }
    }
}