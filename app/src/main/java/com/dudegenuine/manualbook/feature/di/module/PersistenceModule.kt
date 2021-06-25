package com.dudegenuine.manualbook.feature.di.module

import com.dudegenuine.manualbook.feature.datasource.api.RestApi
import com.dudegenuine.manualbook.feature.datasource.domain.ChapterPersistence
import dagger.Module
import dagger.Provides

/**
 * Manual Book created by utifmd on 17/06/21.
 */
@Module
class PersistenceModule {
    @Provides
    fun provideChapterPersistence(restApi: RestApi): ChapterPersistence {
        return ChapterPersistence(restApi)
    }
}