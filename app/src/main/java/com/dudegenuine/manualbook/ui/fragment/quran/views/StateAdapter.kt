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
class StateAdapter(private val viewModel: QuranViewModel, val retry: ()-> Unit): LoadStateAdapter<StateAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemPagerStateBinding): RecyclerView.ViewHolder(binding.root){

        fun bindState(loadState: LoadState) = with(binding) {
            viewModel.onQuranState(loadState)

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

    override fun onBindViewHolder(holder: StateAdapter.ViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState ): StateAdapter.ViewHolder = ViewHolder (
        ItemPagerStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}