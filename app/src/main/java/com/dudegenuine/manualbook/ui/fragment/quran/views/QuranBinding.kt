package com.dudegenuine.manualbook.ui.fragment.quran.views

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.dudegenuine.domain.Quran
import com.dudegenuine.repos.network.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Manual Book created by utifmd on 28/06/21.
 */
object QuranBinding {
    private val TAG: String = javaClass.simpleName

    /*@BindingAdapter("items")
    @JvmStatic fun seItems(view: RecyclerView, resource: Resource<List<Quran>>?){
        with(view.adapter as QuranAdapter){
            resource?.data?.let {
                updateData(it)
            }
        }
    }*/
}