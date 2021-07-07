package com.dudegenuine.manualbook.ui.activity

import android.util.Log
import com.dudegenuine.domain.Chapter
import com.dudegenuine.manualbook.NavGraphHomeFeatureDirections
import com.dudegenuine.manualbook.ui.extention.BaseViewModel

/**
 * Manual Book created by utifmd on 07/07/21.
 */
class MainViewModel: BaseViewModel() {
    private val TAG: String = javaClass.simpleName

    /*
    * Request
    * */

    fun navigateBy() {
        Log.d(TAG, "navigateBy: triggered")
        navigate(NavGraphHomeFeatureDirections.actionGlobalToSearch())
    }

    fun navigateBy(chapter: Chapter) {
        Log.d(TAG, "navigateBy: triggered")
        navigate(NavGraphHomeFeatureDirections.actionGlobalToQuran(chapter))
    }

    /*
    * Listener
    * */

    init {
        dependency().inject(this)
    }
}