package com.dudegenuine.manualbook.feature.di.module

import com.dudegenuine.remote.mapper.ChapterDataMapper
import com.dudegenuine.remote.mapper.QuranDataMapper
import com.dudegenuine.remote.mapper.SearchDataMapper
import com.dudegenuine.remote.mapper.VerseDataMapper
import dagger.Module
import dagger.Provides

/**
 * Manual Book created by utifmd on 17/06/21.
 */
@Module
class MapperModule {
    @Provides
    fun provideChapterMapper(): ChapterDataMapper {
        return ChapterDataMapper()
    }

    @Provides
    fun provideSearchMapper(): SearchDataMapper {
        return SearchDataMapper()
    }

    @Provides
    fun provideQuranMapper(): QuranDataMapper {
        return QuranDataMapper()
    }

    @Provides
    fun provideVerseMapper(): VerseDataMapper {
        return VerseDataMapper()
    }
}