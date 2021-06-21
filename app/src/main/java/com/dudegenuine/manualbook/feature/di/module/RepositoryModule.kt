package com.dudegenuine.manualbook.feature.di.module

import com.dudegenuine.manualbook.feature.datasource.domain.ChapterPersistence
import com.dudegenuine.remote.mapper.ChapterDataMapper
import com.dudegenuine.repository.chapter.ChapterRepository
import dagger.Module
import dagger.Provides

/**
 * Manual Book created by utifmd on 17/06/21.
 */
@Module
class RepositoryModule {
    @Provides
    fun provideChapterRepository(
        persistence: ChapterPersistence,
        mapper: ChapterDataMapper
    ): ChapterRepository {
        return ChapterRepository(persistence, mapper)
    }
}