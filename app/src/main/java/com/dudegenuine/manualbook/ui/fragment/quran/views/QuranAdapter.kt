package com.dudegenuine.manualbook.ui.fragment.quran.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dudegenuine.domain.Quran
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.databinding.ItemQuranBinding
import com.dudegenuine.manualbook.ui.fragment.quran.QuranViewModel

class QuranAdapter(private val viewModel: QuranViewModel): PagingDataAdapter<Quran, RecyclerView.ViewHolder>(DataComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = ViewHolder (
        ItemQuranBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        item?.let {
            (holder as QuranAdapter.ViewHolder).binds(it, position)
        }
    }

    private object DataComparator: DiffUtil.ItemCallback<Quran>(){
        override fun areItemsTheSame(oldItem: Quran, newItem: Quran): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Quran, newItem: Quran): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(val binding: ItemQuranBinding): RecyclerView.ViewHolder(binding.root){
        fun binds(quran: Quran, position: Int) = with(binding){
            title = quran.text

            textView.setOnClickListener { viewModel.onItemSelected(quran) }

            if (position % 2 == 0)
                textView.setBackgroundResource(R.color.reply_white_50_alpha_060)
            else
                textView.setBackgroundResource(R.color.reply_blue_50_alpha_060)
        }
    }
}
