package com.dudegenuine.manualbook.infrastructure.di.module

import com.dudegenuine.manualbook.infrastructure.api.ChapterApi
import com.dudegenuine.manualbook.infrastructure.datasource.chapter.ChapterPersistence
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Manual Book created by utifmd on 17/06/21.
 */
@Module
class PersistenceModule {
    @Provides
    fun provideChapterPersistence(chapterApi: ChapterApi): ChapterPersistence {
        return ChapterPersistence(chapterApi)
    }
}