package com.dudegenuine.local.model.common

import androidx.navigation.NavDirections

/**
 * Manual Book created by utifmd on 20/06/21.
 */
sealed class NavState {
    data class TO(val direction: NavDirections)
        : NavState()
    object BACK
        : NavState()
}