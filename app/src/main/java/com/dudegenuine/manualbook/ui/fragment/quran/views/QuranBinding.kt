package com.dudegenuine.manualbook.ui.fragment.quran.views

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dudegenuine.domain.Chapter
import com.dudegenuine.domain.Quran
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.ui.fragment.home.views.HomeAdapter
import com.dudegenuine.repos.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Manual Book created by utifmd on 28/06/21.
 */
object QuranBinding {
    private val TAG: String = javaClass.simpleName

    @BindingAdapter("showWhenNull")
    @JvmStatic fun <T>showWhenNull(view: SwipeRefreshLayout, resource: T) {
        view.apply {
            isRefreshing = resource == null
        }
    }

    /*@BindingAdapter("items")
    @JvmStatic fun setItems(recyclerView: RecyclerView, resource: PagingData<Quran>?) {
        with(recyclerView.adapter as QuranAdapter) {
            resource?.let { updateData(it) }
        }
    }*/

    /*
    @BindingAdapter("pagingLoadState")
    fun pagingLoadState(view: SwipeRefreshLayout, loadState: LoadState?) {
        if (loadState != null) view.apply { //loadState is LoadState.Loading
            isRefreshing = loadState is LoadState.Loading
            isEnabled = false
        }
    }

    @BindingAdapter("elementPlaceholder")
    fun <T>elementPlaceHolder(view: TextView, resource: T) {
        when(resource){
            is String -> if(resource.length == 0)
                view.setBackgroundResource(R.color.cardview_shadow_end_color)
        }
    }*/
    /*@BindingAdapter("items")
    @JvmStatic fun seItems(view: RecyclerView, resource: Resource<List<Quran>>?){
        with(view.adapter as QuranAdapter){
            resource?.data?.let {
                updateData(it)
            }
        }
    }*/
}