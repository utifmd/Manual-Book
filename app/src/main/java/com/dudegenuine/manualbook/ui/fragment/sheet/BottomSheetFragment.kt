package com.dudegenuine.manualbook.ui.fragment.sheet

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import com.dudegenuine.domain.Chapter
import com.dudegenuine.manualbook.databinding.FragmentSheetBinding
import com.dudegenuine.manualbook.ui.extention.BaseFragment
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.manualbook.ui.fragment.sheet.views.SheetBinding.materialSheetBackground
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import kotlin.LazyThreadSafetyMode.NONE

/**
 * Manual Book created by utifmd on 27/06/21.
 */
class BottomSheetFragment: BaseFragment<FragmentSheetBinding>() {
    private val TAG: String = javaClass.simpleName
    private val sheetViewModel: BottomSheetViewModel by viewModels()

    private val behavior: BottomSheetBehavior<FrameLayout> by lazy(NONE) {
        from(binding.backgroundContainer)
    }

    override fun bindView(): FragmentSheetBinding {
        return FragmentSheetBinding.inflate(layoutInflater)
    }

    override fun bindViewModel(): BaseViewModel = sheetViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@BottomSheetFragment
            vueModel = sheetViewModel

            // behavior.state = STATE_HIDDEN
            backgroundContainer.background = materialSheetBackground (backgroundContainer.context)
        }

        // behavior.addBottomSheetCallback(BehaviorListener)
    }

    fun toggle(){
        when (behavior.state) {
            STATE_HIDDEN -> open()

            STATE_HALF_EXPANDED,
            STATE_EXPANDED,
            STATE_COLLAPSED -> close()

            STATE_DRAGGING -> Unit

            STATE_SETTLING -> Unit
        }
    }

    private fun open() {
        behavior.state = STATE_HALF_EXPANDED
    }

    fun close() { behavior.state = STATE_HIDDEN }

    fun openData(chapter: Chapter) {
        binding.chapter = chapter

        open()
    }

    companion object BehaviorListener : BottomSheetCallback(){
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            println("onStateChanged: $newState")
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            println("onSlide triggered")
        }
    }
}