package com.dudegenuine.manualbook.ui.extention

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import com.dudegenuine.local.model.common.Event
import com.dudegenuine.local.model.common.NavState
import com.dudegenuine.manualbook.feature.di.component.ManualBookComponent

/**
 * Manual Book created by utifmd on 20/06/21.
 */
abstract class BaseViewModel: ViewModel(){

    private val _navigation = MutableLiveData<Event<NavState>>()
    val navigation: LiveData<Event<NavState>> get() = _navigation

    protected val _snackPop = MutableLiveData<Event<String>>()
    val snackPopResource: LiveData<Event<String>> get() = _snackPop

    fun dependency(): ManualBookComponent {
        return ManualBookComponent.createComponent()
    }

    fun navigate(direction: NavDirections, extra: FragmentNavigator.Extras? = null){
        _navigation.value = Event(NavState.TO(direction, extra))
    }

    fun navigateUp(){
        _navigation.value = Event(NavState.BACK)
    }

    /*
    * Remote Params
    * */

    protected fun chaptersParam(lang: String? = null): Map<String, String> = mutableMapOf (
        "language" to (lang ?: "id")
    )

    protected fun searchParam(
        query: String, size: Int? = null, page: Int? = null, lang: String? = null): Map<String, String> =
            mutableMapOf (
                "q" to query,
                "language" to (lang ?: "id")
            ).apply {
                if(size != null) put("size", size.toString())
                if(page != null) put("page", page.toString())
    }

    protected fun verseParam(): Map<String, String> = mutableMapOf (
        //"verse_key" to verseKey, // /1:1?language=id&words=false&translations=134&audio=1
        "language" to "id",
        "words" to "false",
        "translations" to "134",
        "audio" to "1"
    )

    protected fun quranParam(pageNumber: Int): Map<String, String>{
        /*chapter_number=1&
        juz_number=1&
        hizb_number=1&
        rub_number=1&
        verse_key="1:1"*/

        return mapOf("page_number" to pageNumber.toString())
    }
}