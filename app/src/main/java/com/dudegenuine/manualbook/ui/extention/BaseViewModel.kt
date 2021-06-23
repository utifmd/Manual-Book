package com.dudegenuine.manualbook.ui.extention

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.dudegenuine.local.model.common.Event
import com.dudegenuine.local.model.common.NavState
import com.dudegenuine.manualbook.feature.di.component.ManualBookComponent
import com.dudegenuine.manualbook.ui.fragment.home.HomeViewModel

/**
 * Manual Book created by utifmd on 20/06/21.
 */
abstract class BaseViewModel: ViewModel(){
    private val _navigation = MutableLiveData<Event<NavState>>()
    val navigation: LiveData<Event<NavState>> get() = _navigation

    protected val _snackPopError = MutableLiveData<Event<Int>>()
    val snackPopError: LiveData<Event<Int>> get() = _snackPopError

    fun dependency(): ManualBookComponent {
        return ManualBookComponent.createComponent()
    }

    fun navigate(direction: NavDirections){
        _navigation.value = Event(NavState.TO(direction))
    }
}