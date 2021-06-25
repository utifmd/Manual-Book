package com.dudegenuine.manualbook.ui.extention

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.res.use
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.dudegenuine.local.model.common.Event
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.feature.di.component.ManualBookComponent
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialContainerTransform

/**
 * Manual Book created by utifmd on 20/06/21.
 */

fun Fragment.setUpSnackPop(owner: LifecycleOwner, event: LiveData<Event<Int>>, duration: Int){

    event.observe(owner, { e -> e.getContentIfNotHandled()?.let { res ->
        context?.let { popSnackBar(it.getString(res), duration) }
    }})
}

fun Fragment.popSnackBar(message: String, duration: Int) {
    activity?.let {
        Snackbar.make(it.findViewById(android.R.id.content),
            message, duration).show()
    }
}

fun Fragment.bindDestSharedTransition(){
    sharedElementEnterTransition = MaterialContainerTransform().apply {
        drawingViewId = R.id.nav_host_fragment
        duration = resources.getInteger(R.integer.motion_duration_large).toLong()
        scrimColor = Color.TRANSPARENT
        setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
    }
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
