package com.dudegenuine.manualbook.ui.fragment.detail

import android.util.Log
import android.view.View
import com.dudegenuine.manualbook.ui.extention.BaseViewModel

/**
 * Manual Book created by utifmd on 25/06/21.
 */
class DetailViewModel: BaseViewModel() {
    private val TAG: String = javaClass.simpleName
    // TODO: 25/06/21 design chapter's verses mp3 business logic

    /*
    * Listener
    * */

    fun onBackSelected(view: View){
        Log.d(TAG, "onBack: triggered ${view.id}.")
        navigateUp()
    }
}