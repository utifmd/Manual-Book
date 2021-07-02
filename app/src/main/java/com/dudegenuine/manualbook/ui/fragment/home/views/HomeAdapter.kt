package com.dudegenuine.manualbook.ui.fragment.home.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dudegenuine.domain.Chapter
import com.dudegenuine.manualbook.databinding.ItemChapterBinding
import com.dudegenuine.manualbook.ui.extention.DataComparator
import com.dudegenuine.manualbook.ui.fragment.home.HomeViewModel

/**
 * Manual Book created by utifmd on 23/06/21.
 */
class HomeAdapter(
    private val homeViewModel: HomeViewModel): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val items: MutableList<Chapter> = mutableListOf()
    private lateinit var listener: ChapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = HomeViewHolder(
        ItemChapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeViewHolder).binds(items[position], homeViewModel) //, listener)
    }

    override fun getItemCount(): Int = items.size

    fun updateData(list: List<Chapter>) {
        DiffUtil.calculateDiff( DataComparator(items, list) ).apply {
            items.clear()
            items.addAll(list)

            dispatchUpdatesTo(this@HomeAdapter)
        }
    }

    fun updateListener(listener: ChapterListener){
        this.listener = listener
    }

    interface ChapterListener {
        fun onChapterClicked(cardView: View, chapter: Chapter)
        /*fun onChapterLongPressed(chapter: Chapter): Boolean
        fun onChapterStarChanged(chapter: Chapter, newValue: Boolean)
        fun onChapterArchived(chapter: Chapter)*/
    }
}