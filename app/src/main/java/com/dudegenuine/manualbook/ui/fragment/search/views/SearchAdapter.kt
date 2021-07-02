package com.dudegenuine.manualbook.ui.fragment.search.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dudegenuine.domain.Search
import com.dudegenuine.manualbook.databinding.SearchItemBinding
import com.dudegenuine.manualbook.ui.extention.DataComparator
import com.dudegenuine.manualbook.ui.fragment.search.SearchViewModel

/**
 * Manual Book created by utifmd on 26/06/21.
 */
class SearchAdapter(
    private val viewModel: SearchViewModel): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<Search> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = SearchViewHolder(
        SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as SearchViewHolder).binds(items[position], viewModel)

    override fun getItemCount(): Int = items.size

    fun updateData(list: List<Search>){
        DiffUtil.calculateDiff( DataComparator(items, list) ).apply {
            items.clear()
            items.addAll(list)

            dispatchUpdatesTo(this@SearchAdapter)
        }
    }
}