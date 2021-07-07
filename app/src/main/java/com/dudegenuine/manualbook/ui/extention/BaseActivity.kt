package com.dudegenuine.manualbook.ui.extention

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.viewbinding.ViewBinding
import com.dudegenuine.local.model.common.NavState
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.ui.fragment.sheet.BottomSheetFragment
import com.google.android.material.snackbar.Snackbar

/**
 * Manual Book created by utifmd on 07/07/21.
 */
abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {
    private lateinit var _binding: VB
    protected val binding get() = _binding

    protected lateinit var navController: NavController
    protected lateinit var appBarConfiguration: AppBarConfiguration

    protected val sheetFragment: BottomSheetFragment by lazy(LazyThreadSafetyMode.NONE) {
        supportFragmentManager.findFragmentById(R.id.bottom_sheet_fragment) as BottomSheetFragment
    }

    abstract fun bindView(): VB

    abstract fun bindViewModel(): BaseViewModel?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindView()

        setContentView(_binding.root)
        setInitialView()
        setInitialNavigation()
    }

    private fun setInitialView() {
        bindViewModel()?.let {
            setUpNavigation(it)
            setUpSnackPop(this, it.snackPopResource, Snackbar.LENGTH_LONG)
        }
    }

    private fun setInitialNavigation() {
        navController = findNavController(R.id.nav_host_fragment).apply {
            appBarConfiguration = AppBarConfiguration(this.graph)
        } // setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setUpNavigation(viewModel: BaseViewModel?) {
        viewModel?.navigation?.observe(this, {
            it?.getContentIfNotHandled()?.let { command -> when(command) {
                is NavState.TO -> command.extra?.let { isExtra ->
                    navController.navigate(command.direction, isExtra )
                }
                is NavState.BACK ->
                    navController.navigateUp()
            }}
        })
    }
}