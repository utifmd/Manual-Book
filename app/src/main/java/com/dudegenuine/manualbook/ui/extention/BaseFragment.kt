package com.dudegenuine.manualbook.ui.extention

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.dudegenuine.local.model.common.NavState

/**
 * Manual Book created by utifmd on 18/06/21.
 */
abstract class BaseFragment<VB: ViewBinding>: Fragment(){
    private lateinit var _binding: VB // ? = null
    val binding get() = _binding

    abstract fun bindView(): VB

    abstract fun bindViewModel(): BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        _binding = bindView()

        setUpVueModel(bindViewModel())
        setUpSnackPop(this, bindViewModel().eventSnackError, Snackbar.LENGTH_LONG)

        return binding.root
    }

    private fun setUpVueModel(viewModel: BaseViewModel) {
        viewModel.eventNav.observe(viewLifecycleOwner, {
            it?.getContentIfNotHandled()?.let { command ->
                when(command){
                    is NavState.TO ->
                        findNavController().navigate(command.direction, getExtras())

                    is NavState.BACK ->
                        findNavController().navigateUp()
                }
            }
        })
    }

    open fun getExtras(): FragmentNavigator.Extras =
        FragmentNavigatorExtras()
}