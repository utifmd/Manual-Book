package com.dudegenuine.manualbook

import android.app.Application

/**
 * Manual Book created by utifmd on 17/06/21.
 */
class ManualBook: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        lateinit var instance: ManualBook

        fun creteInstance(): ManualBook =
            instance
    }
}