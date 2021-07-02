package com.dudegenuine.manualbook.ui.fragment.sheet

import android.util.Log
import android.view.View
import com.dudegenuine.manualbook.ui.extention.BaseViewModel

/**
 * Manual Book created by utifmd on 27/06/21.
 */
class BottomSheetViewModel: BaseViewModel() {
    private val TAG: String = javaClass.simpleName

    /*
    * Listener
    * */

    fun onItemSelected(view: View){
        Log.d(TAG, "onItemSelected: ${view.id}")
    }
}