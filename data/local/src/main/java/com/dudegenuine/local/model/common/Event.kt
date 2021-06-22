package com.dudegenuine.local.model.common

/**
 * Manual Book created by utifmd on 22/06/21.
 */
class Event<out T>(private val content: T){
    private var hasBeenHandled: Boolean = false

    fun peekContent(): T = content

    fun getContentIfNotHandled(): T?{
        return if (!hasBeenHandled){
            hasBeenHandled = true
            content
        } else null
    }
}