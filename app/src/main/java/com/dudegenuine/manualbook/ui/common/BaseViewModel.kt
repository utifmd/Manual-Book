package com.dudegenuine.manualbook.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.dudegenuine.manualbook.ui.common.state.NavigationCommand

/**
 * Manual Book created by utifmd on 20/06/21.
 */
/*
abstract class BaseViewModel: ViewModel(){
    private lateinit var _navigation: MutableLiveData<Event<NavigationCommand>>
    val eventNav get() = _navigation

    private lateinit var _snackPopError: MutableLiveData<Event<Int>>
    val eventSnackError get() = _snackPopError

    fun navigate(direction: NavDirections){
        eventNav.value = Event(NavigationCommand.TO(direction))
    }
}*/
