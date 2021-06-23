package com.dudegenuine.manualbook.ui.extention

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.dudegenuine.local.model.common.Event
import com.dudegenuine.local.model.common.NavState
import com.dudegenuine.manualbook.feature.di.component.ManualBookComponent

/**
 * Manual Book created by utifmd on 20/06/21.
 */
abstract class BaseViewModel: ViewModel(){
    private lateinit var _navigation: MutableLiveData<Event<NavState>>
    val eventNav get() = _navigation

    private lateinit var _snackPopError: MutableLiveData<Event<Int>>
    val eventSnackError get() = _snackPopError

    fun navigate(direction: NavDirections){
        eventNav.value = Event(NavState.TO(direction))
    }
}