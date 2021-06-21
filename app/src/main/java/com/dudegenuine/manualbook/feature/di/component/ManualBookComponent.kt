package com.dudegenuine.manualbook.feature.di.component

import androidx.lifecycle.ViewModel
import com.dudegenuine.manualbook.feature.di.module.*
import com.dudegenuine.manualbook.feature.di.module.network.NetworkModule
import com.dudegenuine.manualbook.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Manual Book created by utifmd on 17/06/21.
 */
@Singleton
@Component( modules = [
    AndroidModule::class,
    NetworkModule::class,
    ApiModule::class,
    MapperModule::class,
    PersistenceModule::class,
    RepositoryModule::class,
    UseCaseModule::class
] )
interface ManualBookComponent {
    fun inject(viewModel: ViewModel)
    fun inject(mainActivity: MainActivity)

    companion object {
        fun createComponent(): ManualBookComponent {
            return DaggerManualBookComponent.create()
        }
    }
}