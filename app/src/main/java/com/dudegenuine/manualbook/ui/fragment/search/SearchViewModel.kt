package com.dudegenuine.manualbook.ui.fragment.search

import android.view.View
import com.dudegenuine.manualbook.ui.extention.BaseViewModel

/**
 * Manual Book created by utifmd on 25/06/21.
 */
class SearchViewModel: BaseViewModel() {
    // TODO: 25/06/21 inject search data


    /*
    * Listener
    * */

    fun onBackSelected(view: View) = navigateUp()
}