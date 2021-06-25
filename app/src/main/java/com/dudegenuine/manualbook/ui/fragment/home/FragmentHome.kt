package com.dudegenuine.manualbook.ui.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.dudegenuine.domain.Chapter
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.databinding.FragmentHomeBinding
import com.dudegenuine.manualbook.ui.extention.BaseFragment
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.manualbook.ui.fragment.home.views.HomeAdapter
import com.dudegenuine.manualbook.ui.fragment.home.views.HomeBinding.bindTransPostpone
import com.google.android.material.transition.MaterialElevationScale

/**
 * Manual Book created by utifmd on 20/06/21.
 */
class FragmentHome: BaseFragment<FragmentHomeBinding>() {
    private val TAG: String = javaClass.simpleName
    private val viewModel: HomeViewModel by viewModels()

    override fun bindView(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun bindViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindTransPostpone(binding)
        binding.apply {
            lifecycleOwner = this@FragmentHome
            homeViewModel = viewModel

            recyclerView.apply {
                adapter = HomeAdapter(viewModel)
            }
        }
    }
}
