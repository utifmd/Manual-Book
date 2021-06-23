package com.dudegenuine.manualbook.ui.fragment.home.views

import androidx.recyclerview.widget.RecyclerView
import com.dudegenuine.domain.Chapter
import com.dudegenuine.manualbook.databinding.ItemLayoutChapterBinding
import com.dudegenuine.manualbook.ui.fragment.home.HomeViewModel

/**
 * Manual Book created by utifmd on 23/06/21.
 */
class HomeViewHolder(
    private val binding: ItemLayoutChapterBinding): RecyclerView.ViewHolder(binding.root) {

    fun binds(chapter: Chapter, viewModel: HomeViewModel){

    }
}