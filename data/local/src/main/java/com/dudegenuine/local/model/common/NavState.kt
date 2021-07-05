package com.dudegenuine.local.model.common

import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator

/**
 * Manual Book created by utifmd on 20/06/21.
 */
sealed class NavState {
    data class TO(val direction: NavDirections, val extra: FragmentNavigator.Extras?)
        : NavState()
    object BACK
        : NavState()
}