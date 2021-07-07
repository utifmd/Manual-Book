package com.dudegenuine.manualbook.ui.fragment.quran.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dudegenuine.domain.Quran
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.databinding.ItemQuranBinding

import android.view.View


class QuranAdapter: PagingDataAdapter<Quran, RecyclerView.ViewHolder>(DataComparator) {
    private lateinit var listener: Listener

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
            front = "${position+1}"
            title = quran.text // "${quran.text}\n${quran.audioUrl}"

            textView.setOnClickListener { listener.onItemSelected(quran) }
            btnPlay.setOnClickListener { listener.onAudioSelected(quran, position) }

            if (position % 2 == 0)
                root.setBackgroundResource(R.color.reply_white_50_alpha_060)
            else
                root.setBackgroundResource(R.color.reply_blue_50_alpha_060)
        }
    }

    fun setListener(listener: Listener){
        this.listener = listener
    }

    interface Listener {
        fun onItemSelected(quran: Quran)
        fun onEventPlaySelected(view: View)
        fun onAudioSelected(quran: Quran, position: Int)

        interface Callback {
            fun onAudioPlayed()
        }
    }
}
