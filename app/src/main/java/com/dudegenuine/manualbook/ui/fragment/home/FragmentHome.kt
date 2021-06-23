package com.dudegenuine.manualbook.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.dudegenuine.manualbook.databinding.FragmentHomeBinding
import com.dudegenuine.manualbook.ui.extention.BaseFragment
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.manualbook.ui.fragment.home.views.HomeAdapter

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

        binding.apply {
            lifecycleOwner = this@FragmentHome
            homeViewModel = viewModel

            recyclerView.apply {
                adapter = HomeAdapter(viewModel)
            }
        }
    }
}
