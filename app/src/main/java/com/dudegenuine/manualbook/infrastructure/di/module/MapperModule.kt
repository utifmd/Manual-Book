package com.dudegenuine.manualbook.infrastructure.di.module

import com.dudegenuine.data.remote.persistance.mapper.ChapterDataMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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