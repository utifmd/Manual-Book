package com.dudegenuine.manualbook.feature.di.component

import com.dudegenuine.manualbook.feature.di.module.*
import com.dudegenuine.manualbook.feature.di.module.network.NetworkModule
import com.dudegenuine.manualbook.ui.fragment.home.HomeViewModel
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
    UseCaseModule::class,
    ViewModelModule::class
] )
interface ManualBookComponent {
    fun inject(homeViewModel: HomeViewModel)

    companion object {
        fun createComponent(): ManualBookComponent {
            return DaggerManualBookComponent.create()
        }
    }
}