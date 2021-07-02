package com.dudegenuine.manualbook.ui.fragment.search.views

import android.app.Activity
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dudegenuine.domain.Chapter
import com.dudegenuine.domain.Search
import com.dudegenuine.manualbook.ui.fragment.home.views.HomeAdapter
import com.dudegenuine.repos.network.Resource

/**
 * Manual Book created by utifmd on 26/06/21.
 */
object SearchBinding {
    private val TAG: String = javaClass.simpleName

    @BindingAdapter("items")
    @JvmStatic fun setItems(recyclerView: RecyclerView, resource: Resource<List<Search>>?) {
        with(recyclerView.adapter as SearchAdapter) {
            resource?.data?.let { updateData(it) }
        }
    }

    @BindingAdapter("bindListener")
    @JvmStatic fun bindCommonEvent(view: EditText, listener: (String) -> Unit){
        view.setOnKeyListener { _, _, event ->
            if (event.action == KeyEvent.ACTION_DOWN ||
                event.action == KeyEvent.KEYCODE_ENTER){

                if (view.text.isNullOrEmpty())
                    view.error = "Cari disini."

                else {
                    listener(view.text.toString())

                    view.setText("")
                    view.clearFocus()
                    view.hideKeyboard()
                }
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    /*
    * Extension
    * */

    fun EditText.hideKeyboard(): Boolean {
        return (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(windowToken, 0)
    }

    /*fun EditText.showKeyboard(): Boolean {
        return (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(windowToken, 0)
    }*/
}