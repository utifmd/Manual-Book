package com.dudegenuine.manualbook.ui.fragment.home.views

import android.util.Log
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dudegenuine.domain.Chapter
import com.dudegenuine.repos.network.Resource

/**
 * Manual Book created by utifmd on 23/06/21.
 */
object HomeBinding {
    private val TAG: String = javaClass.simpleName

    @BindingAdapter("showWhenLoading")
    @JvmStatic fun <T>showWhenLoading(view: SwipeRefreshLayout, resource: Resource<T>?) {
        if (resource != null) view.isRefreshing =
            resource.status == Resource.Status.LOADING
    }

    @BindingAdapter("items")
    @JvmStatic fun setItems(recyclerView: RecyclerView, resource: Resource<List<Chapter>>?) {
        with(recyclerView.adapter as HomeAdapter) {
            resource?.data?.let { updateData(it) }
        }
    }

    fun Fragment.bindTransPostpone(binding: ViewDataBinding){
        postponeEnterTransition()
        binding.root.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    /*@BindingAdapter("app:imageUrl")
    @JvmStatic fun loadImage(view: ImageView, url: String) {
        Glide.with(view.context).load(url).apply(RequestOptions.circleCropTransform()).into(view)
    }*/

    /*@BindingAdapter("app:showWhenEmptyList")
    @JvmStatic fun showMessageErrorWhenEmptyList(view: View, resource: Resource<List<Chapter>>?) {
        if (resource!=null) {
            view.visibility = if (resource.status == Resource.Status.ERROR
                && resource.data != null
                && resource.data!!.isEmpty()) View.VISIBLE else View.GONE
        }
    }*/
}