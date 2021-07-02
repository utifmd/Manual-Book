package com.dudegenuine.manualbook.ui.fragment.quran

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.dudegenuine.manualbook.databinding.FragmentQuranBinding
import com.dudegenuine.manualbook.ui.extention.BaseFragment
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.manualbook.ui.extention.bindEnterReturnTransition
import com.dudegenuine.manualbook.ui.fragment.quran.views.QuranAdapter
import com.dudegenuine.manualbook.ui.fragment.quran.views.StateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Manual Book created by utifmd on 27/06/21.
 */
class QuranFragment: BaseFragment<FragmentQuranBinding>() {
    private val TAG: String = javaClass.simpleName
    private val vueModel: QuranViewModel by viewModels()
    private val args: QuranFragmentArgs by navArgs()

    override fun bindView(): FragmentQuranBinding =
        FragmentQuranBinding.inflate(layoutInflater)

    override fun bindViewModel(): BaseViewModel = vueModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val quranAdapter = QuranAdapter(vueModel)

        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = quranAdapter
                setHasFixedSize(true)
            }
        }

        lifecycleScope.launch {
            vueModel.verses(args.chapter.pages[0], args.chapter.pages[1]).collectLatest {
                quranAdapter.submitData(it)
            }
        }

        quranAdapter.withLoadStateFooter(
            footer = StateAdapter { quranAdapter.retry() } // header = StateAdapter { quranAdapter.retry() },
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindEnterReturnTransition()
    }
}