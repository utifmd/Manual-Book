package com.dudegenuine.manualbook.feature.di.module

import com.dudegenuine.manualbook.feature.di.module.network.INetwork
import com.dudegenuine.manualbook.feature.datasource.api.ChapterApi
import dagger.Module
import dagger.Provides

/**
 * Manual Book created by utifmd on 17/06/21.
 */
@Module
class ApiModule {
    @Provides
    fun provideChapterApi(network: INetwork): ChapterApi =
        network.builder().build().create(ChapterApi::class.java) // retrofit.create(ChapterApi::class.java)
}