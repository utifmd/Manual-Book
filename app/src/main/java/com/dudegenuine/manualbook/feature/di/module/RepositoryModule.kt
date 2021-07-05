package com.dudegenuine.manualbook.feature.di.module

import com.dudegenuine.manualbook.feature.datasource.domain.ChapterPersistence
import com.dudegenuine.manualbook.feature.datasource.domain.QuranPersistence
import com.dudegenuine.manualbook.feature.datasource.domain.SearchPersistence
import com.dudegenuine.manualbook.feature.datasource.domain.VersePersistence
import com.dudegenuine.remote.mapper.ChapterDataMapper
import com.dudegenuine.remote.mapper.QuranDataMapper
import com.dudegenuine.remote.mapper.SearchDataMapper
import com.dudegenuine.remote.mapper.VerseDataMapper
import com.dudegenuine.remote.persistence.IQuranPersistence
import com.dudegenuine.repos.domain.chapter.ChapterRepository
import com.dudegenuine.repos.domain.chapter.IChapterRepository
import com.dudegenuine.repos.domain.quran.IQuranRepository
import com.dudegenuine.repos.domain.quran.QuranRepository
import com.dudegenuine.repos.domain.search.ISearchRepository
import com.dudegenuine.repos.domain.search.SearchRepository
import com.dudegenuine.repos.domain.verse.IVerseRepository
import com.dudegenuine.repos.domain.verse.VerseRepository
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
        mapper: ChapterDataMapper): IChapterRepository {
        return ChapterRepository(persistence, mapper)
    }

    @Provides
    fun provideSearchRepository(
        persistence: SearchPersistence,
        mapper: SearchDataMapper): ISearchRepository {

        return SearchRepository(persistence, mapper)
    }

    @Provides
    fun provideQuranRepository(
        persistence: QuranPersistence,
        mapper: QuranDataMapper): IQuranRepository {

        return QuranRepository(persistence, mapper)
    }

    @Provides
    fun provideVerseRepository(
        persistence: VersePersistence,
        mapper: VerseDataMapper): IVerseRepository {

        return VerseRepository(persistence, mapper)
    }
}