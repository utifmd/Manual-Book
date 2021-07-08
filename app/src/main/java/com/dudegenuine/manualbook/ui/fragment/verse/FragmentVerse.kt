package com.dudegenuine.manualbook.ui.fragment.verse

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.dudegenuine.manualbook.databinding.FragmentVerseBinding
import com.dudegenuine.manualbook.ui.extention.*

/**
 * Manual Book created by utifmd on 03/07/21.
 */
class FragmentVerse: BaseFragment<FragmentVerseBinding>() {
    private val TAG: String = javaClass.simpleName

    private val vueModel: VerseViewModel by viewModels()
    private val args: FragmentVerseArgs by navArgs()

    override fun bindView(): FragmentVerseBinding {
        return FragmentVerseBinding.inflate(layoutInflater)
    }

    override fun bindViewModel(): BaseViewModel = vueModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@FragmentVerse
            chapter = args.chapter

            vueModel.verse(args.quran.verseKey).observe(viewLifecycleOwner, {
                it?.data?.let { itVerse ->
                    quran = args.quran
                    verse = itVerse
                    viewBody = parseHtml(
                        itVerse.translationText
                    )
                }
            })

            partDetail.navigateBack.setOnClickListener(vueModel::onBackSelected)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindEnterReturnTransition()
    }
}