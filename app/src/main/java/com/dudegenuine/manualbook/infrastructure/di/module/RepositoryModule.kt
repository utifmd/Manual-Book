package com.dudegenuine.manualbook.infrastructure.di.module

import com.dudegenuine.data.remote.persistance.mapper.ChapterDataMapper
import com.dudegenuine.data.remote.persistance.repository.ChapterRepository
import com.dudegenuine.manualbook.infrastructure.datasource.chapter.ChapterPersistence
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Manual Book created by utifmd on 17/06/21.
 */
@Module
class RepositoryModule {
    @Provides
    fun provideChapterRepository(
        persistence: ChapterPersistence,
        mapper: ChapterDataMapper): ChapterRepository {
        return ChapterRepository(persistence, mapper)
    }
}