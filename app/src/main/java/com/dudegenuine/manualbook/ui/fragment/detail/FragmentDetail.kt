package com.dudegenuine.manualbook.ui.fragment.detail

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import androidx.savedstate.SavedStateRegistryOwner
import com.dudegenuine.domain.Chapter
import com.dudegenuine.manualbook.databinding.FragmentDetailBinding
import com.dudegenuine.manualbook.ui.extention.*

/**
 * Manual Book created by utifmd on 24/06/21.
 */
class FragmentDetail: BaseFragment<FragmentDetailBinding>() {
    private val TAG: String = javaClass.simpleName

    private val args: FragmentDetailArgs by navArgs()

    private val vueModel: DetailViewModel by viewModels {
        BaseViewModelFactory(DetailViewModel.DetailFactory, this, arguments)
    }

    override fun bindView(): FragmentDetailBinding =
        FragmentDetailBinding.inflate(layoutInflater)

    override fun bindViewModel(): BaseViewModel = vueModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindDestSharedTransition()
        bindExitRenterTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@FragmentDetail
            viewModel = vueModel
            chapter = args.chapter

            partDetail.navigateBack.setOnClickListener(vueModel::onBackSelected)
        }
    }
}