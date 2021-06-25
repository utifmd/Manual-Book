package com.dudegenuine.manualbook.ui.fragment.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.dudegenuine.manualbook.databinding.FragmentSearchBinding
import com.dudegenuine.manualbook.ui.extention.BaseFragment
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.manualbook.ui.extention.bindEnterReturnTransition

/**
 * Manual Book created by utifmd on 25/06/21.
 */
class FragmentSearch: BaseFragment<FragmentSearchBinding>() {
    private val TAG: String = javaClass.simpleName
    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            searchToolbar.setNavigationOnClickListener(viewModel::onBackSelected)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindEnterReturnTransition()
    }

    override fun bindViewModel(): BaseViewModel = viewModel

    override fun bindView(): FragmentSearchBinding =
        FragmentSearchBinding.inflate(layoutInflater)
}