package com.dudegenuine.manualbook.ui.fragment.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.databinding.FragmentDetailBinding
import com.dudegenuine.manualbook.ui.extention.BaseFragment
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.manualbook.ui.extention.bindDestSharedTransition
import com.dudegenuine.manualbook.ui.extention.themeColor
import com.google.android.material.transition.MaterialContainerTransform

/**
 * Manual Book created by utifmd on 24/06/21.
 */
class FragmentDetail: BaseFragment<FragmentDetailBinding>() {
    private val TAG: String = javaClass.simpleName
    private val viewModel: DetailViewModel by viewModels()
    private val args: FragmentDetailArgs by navArgs()

    override fun bindView(): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(layoutInflater)
    }

    override fun bindViewModel(): BaseViewModel = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindDestSharedTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@FragmentDetail
            chapter = this@FragmentDetail.args.chapter
        }
    }
}