package com.dudegenuine.manualbook.feature.di.component

import com.dudegenuine.manualbook.feature.di.module.*
import com.dudegenuine.manualbook.feature.di.module.network.NetworkModule
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.manualbook.ui.fragment.detail.DetailViewModel
import com.dudegenuine.manualbook.ui.fragment.home.HomeViewModel
import com.dudegenuine.manualbook.ui.fragment.quran.QuranViewModel
import com.dudegenuine.manualbook.ui.fragment.search.SearchViewModel
import com.dudegenuine.manualbook.ui.fragment.verse.VerseViewModel
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
    fun inject(detailViewModel: DetailViewModel)
    fun inject(searchViewModel: SearchViewModel)
    fun inject(quranViewModel: QuranViewModel)
    fun inject(verseViewModel: VerseViewModel)

    companion object {
        fun createComponent(): ManualBookComponent {
            return DaggerManualBookComponent.create()
        }
    }
}