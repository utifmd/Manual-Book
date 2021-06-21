package com.dudegenuine.manualbook.feature.di.module

import com.dudegenuine.data.remote.persistance.mapper.ChapterDataMapper
import dagger.Module
import dagger.Provides

/**
 * Manual Book created by utifmd on 17/06/21.
 */
@Module
class MapperModule {
    @Provides
    fun provideChapterMapper(): ChapterDataMapper{
        return ChapterDataMapper()
    }
}