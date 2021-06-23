package com.dudegenuine.manualbook.ui.fragment.home.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dudegenuine.domain.Chapter
import com.dudegenuine.manualbook.databinding.ItemLayoutChapterBinding
import com.dudegenuine.manualbook.ui.fragment.home.HomeViewModel

/**
 * Manual Book created by utifmd on 23/06/21.
 */
class HomeAdapter(val homeViewModel: HomeViewModel): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val items: List<Chapter> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = HomeViewHolder(
        ItemLayoutChapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeViewHolder).binds(items[position], homeViewModel)
    }

    override fun getItemCount(): Int = items.size

    fun updateData(list: List<Chapter>) {

    }
}