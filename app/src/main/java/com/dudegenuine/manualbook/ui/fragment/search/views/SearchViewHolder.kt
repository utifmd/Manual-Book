package com.dudegenuine.manualbook.ui.fragment.search.views

import androidx.recyclerview.widget.RecyclerView
import com.dudegenuine.domain.Search
import com.dudegenuine.manualbook.databinding.SearchItemBinding
import com.dudegenuine.manualbook.ui.fragment.search.SearchViewModel

/**
 * Manual Book created by utifmd on 26/06/21.
 */
class SearchViewHolder(
    private val binding: SearchItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun binds(mSearch: Search, mViewModel: SearchViewModel) = with(binding) {
        search = mSearch
        viewModel = mViewModel

        executePendingBindings()
    }
}