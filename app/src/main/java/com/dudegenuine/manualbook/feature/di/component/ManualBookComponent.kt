package com.dudegenuine.manualbook.feature.di.component

import com.dudegenuine.manualbook.feature.di.module.*
import com.dudegenuine.manualbook.feature.di.module.NetworkModule
import com.dudegenuine.manualbook.ui.activity.MainViewModel
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
@Component( modules = [AndroidModule::class, NetworkModule::class, ApiModule::class,
    MapperModule::class, PersistenceModule::class, RepositoryModule::class, UseCaseModule::class] )
interface ManualBookComponent {

    @Component.Factory
    interface Factory{
        fun create(androidModule: AndroidModule): ManualBookComponent
    }

    fun inject(mainViewModel: MainViewModel)
    fun inject(homeViewModel: HomeViewModel)
    fun inject(detailViewModel: DetailViewModel)
    fun inject(searchViewModel: SearchViewModel)
    fun inject(quranViewModel: QuranViewModel)
    fun inject(verseViewModel: VerseViewModel)
}