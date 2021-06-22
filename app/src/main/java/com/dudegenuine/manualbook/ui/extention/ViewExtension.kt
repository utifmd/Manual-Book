package com.dudegenuine.manualbook.ui.extention

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.dudegenuine.local.model.common.Event
import com.google.android.material.snackbar.Snackbar

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
