package com.dudegenuine.manualbook.ui.extention

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.use
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dudegenuine.local.model.common.Event
import com.dudegenuine.manualbook.R
import com.dudegenuine.repos.network.Resource
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialSharedAxis

/**
 * Manual Book created by utifmd on 20/06/21.
 */


fun AppCompatActivity.setUpSnackPop(owner: LifecycleOwner, event: LiveData<Event<String>>, duration: Int){
    event.observe(owner, { e -> e.getContentIfNotHandled()?.let { res ->
        popSnackBar(res, duration)
    }})
}

fun AppCompatActivity.popSnackBar(message: String, duration: Int) {
    Snackbar.make(findViewById(android.R.id.content),
        message, duration).show()
}

fun Fragment.setUpSnackPop(owner: LifecycleOwner, event: LiveData<Event<String>>, duration: Int){

    event.observe(owner, { e -> e.getContentIfNotHandled()?.let { res ->
        context?.let { popSnackBar(res, duration) }
    }})
}

fun Fragment.popSnackBar(message: String, duration: Int) {
    activity?.let {
        Snackbar.make(it.findViewById(android.R.id.content),
            message, duration).show()
    }
}

/*
* Transition common
* */

fun Fragment.bindDestSharedTransition(){
    sharedElementEnterTransition = MaterialContainerTransform().apply {
        drawingViewId = R.id.nav_host_fragment
        duration = resources.getInteger(R.integer.motion_duration_large).toLong()
        scrimColor = Color.TRANSPARENT
        setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
    }
}

fun Fragment.bindEnterReturnTransition(){
    enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
        duration = resources.getInteger(R.integer.motion_duration_large).toLong()
    }
    returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
        duration = resources.getInteger(R.integer.motion_duration_large).toLong()
    }
}

fun Fragment.bindExitRenterTransition(){
    exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
        duration = resources.getInteger(R.integer.motion_duration_large).toLong()
    }
    reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
        duration = resources.getInteger(R.integer.motion_duration_large).toLong()
    }
}

/*
* Binding Adapter
* */

@BindingAdapter("showWhenLoading")
fun <T>showWhenLoading(view: SwipeRefreshLayout, resource: Resource<T>?) {
    if (resource != null) {
        view.isRefreshing = resource.status == Resource.Status.LOADING

        resource.data?.let {
            view.isEnabled = false
        }
    }
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@ColorInt
@SuppressLint("Recycle")
fun Context.themeColor(
    @AttrRes themeAttrId: Int
): Int {
    return obtainStyledAttributes(
        intArrayOf(themeAttrId)
    ).use {
        it.getColor(0, Color.MAGENTA)
    }
}
