package com.dudegenuine.manualbook.ui.fragment.search.views

import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.dudegenuine.domain.Search
import com.dudegenuine.manualbook.databinding.SearchItemBinding
import com.dudegenuine.manualbook.ui.fragment.search.SearchViewModel

/**
 * Manual Book created by utifmd on 26/06/21.
 */
class SearchViewHolder(
    private val binding: SearchItemBinding): RecyclerView.ViewHolder(binding.root) {

    @RequiresApi(Build.VERSION_CODES.N)
    fun binds(mSearch: Search, mViewModel: SearchViewModel) = with(binding) {
        viewModel = mViewModel
        highlight = Html.fromHtml(mSearch.textOrig, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
        body = Html.fromHtml(mSearch.textTrans, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()

        executePendingBindings()
    }
}