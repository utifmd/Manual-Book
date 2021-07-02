package com.dudegenuine.manualbook.ui.fragment.home.views

import androidx.recyclerview.widget.RecyclerView
import com.dudegenuine.domain.Chapter
import com.dudegenuine.manualbook.databinding.ItemChapterBinding
import com.dudegenuine.manualbook.ui.fragment.home.HomeViewModel

/**
 * Manual Book created by utifmd on 23/06/21.
 */
class HomeViewHolder(
    private val binding: ItemChapterBinding): RecyclerView.ViewHolder(binding.root) {

    fun binds(mChapter: Chapter, mViewModel: HomeViewModel) = with(binding) {
        chapter = mChapter
        homeViewModel = mViewModel

        executePendingBindings()
        // chapterListener = mListener
    } // , mListener: HomeAdapter.ChapterListener?){
}