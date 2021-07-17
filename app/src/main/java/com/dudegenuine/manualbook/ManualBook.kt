package com.dudegenuine.manualbook

import android.app.Application
import com.dudegenuine.manualbook.feature.di.component.DaggerManualBookComponent
import com.dudegenuine.manualbook.feature.di.component.ManualBookComponent
import com.dudegenuine.manualbook.feature.di.module.AndroidModule

/**
 * Manual Book created by utifmd on 17/06/21.
 */
class ManualBook: Application(){

    companion object{
        lateinit var instance: ManualBook

        fun creteInstance(): ManualBook =
            instance
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}