package com.dudegenuine.manualbook.ui.common.state

import androidx.navigation.NavDirections

/**
 * Manual Book created by utifmd on 20/06/21.
 */
sealed class NavigationCommand {
    data class TO(val direction: NavDirections)
        : NavigationCommand()
    object BACK
        : NavigationCommand()
}