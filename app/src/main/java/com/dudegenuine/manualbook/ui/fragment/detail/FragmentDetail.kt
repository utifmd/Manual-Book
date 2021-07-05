package com.dudegenuine.manualbook.ui.fragment.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.dudegenuine.manualbook.databinding.FragmentDetailBinding
import com.dudegenuine.manualbook.ui.extention.*

/**
 * Manual Book created by utifmd on 24/06/21.
 */

// TODO: 05/07/21 implement media player
class FragmentDetail: BaseFragment<FragmentDetailBinding>() {
    private val TAG: String = javaClass.simpleName
    private val vueModel: DetailViewModel by viewModels()
    private val args: FragmentDetailArgs by navArgs()

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
            chapter = args.chapter // quran = this@FragmentDetail.args.quran

            partDetail.navigateBack.setOnClickListener(vueModel::onBackSelected)
        }
    }
}