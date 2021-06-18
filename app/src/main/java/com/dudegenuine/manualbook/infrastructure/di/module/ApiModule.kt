package com.dudegenuine.manualbook.infrastructure.di.module

import com.dudegenuine.manualbook.infrastructure.di.module.network.INetwork
import com.dudegenuine.manualbook.infrastructure.api.ChapterApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Manual Book created by utifmd on 17/06/21.
 */
@Module
class ApiModule {
    @Provides
    fun provideChapterApi(network: INetwork): ChapterApi =
        network.builder().build().create(ChapterApi::class.java)
        // retrofit.create(ChapterApi::class.java)
}