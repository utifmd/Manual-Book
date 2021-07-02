package com.dudegenuine.manualbook.ui.fragment.quran.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dudegenuine.manualbook.databinding.ItemPagerStateBinding
import com.dudegenuine.manualbook.databinding.ItemQuranBinding
import com.dudegenuine.manualbook.ui.extention.visible
import com.dudegenuine.manualbook.ui.fragment.quran.QuranViewModel

/**
 * Manual Book created by utifmd on 02/07/21.
 */
class StateAdapter(val retry: ()-> Unit): LoadStateAdapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, loadState: LoadState) {
        (holder as StateAdapter.ViewHolder).bindState(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): RecyclerView.ViewHolder = ViewHolder (
        ItemPagerStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    inner class ViewHolder(val binding: ItemPagerStateBinding): RecyclerView.ViewHolder(binding.root){

        fun bindState(loadState: LoadState) = with(binding) {
            if (loadState is LoadState.Error) {
                textViewError.text = loadState.error.localizedMessage
            }
            progressBar.visible(loadState is LoadState.Loading)
            btnRetry.visible(loadState is LoadState.Error)
            textViewError.visible(loadState is LoadState.Error)
            btnRetry.setOnClickListener {
                retry()
            }
        }
    }
}