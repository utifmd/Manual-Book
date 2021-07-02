package com.dudegenuine.manualbook.feature.di.module

import com.dudegenuine.manualbook.feature.datasource.api.RestApi
import com.dudegenuine.manualbook.feature.datasource.domain.ChapterPersistence
import com.dudegenuine.manualbook.feature.datasource.domain.QuranPersistence
import com.dudegenuine.manualbook.feature.datasource.domain.SearchPersistence
import com.dudegenuine.remote.persistence.IChapterPersistence
import com.dudegenuine.remote.persistence.IQuranPersistence
import com.dudegenuine.remote.persistence.ISearchPersistence
import dagger.Module
import dagger.Provides

/**
 * Manual Book created by utifmd on 17/06/21.
 */
@Module
class PersistenceModule {
    @Provides
    fun provideChapterPersistence(restApi: RestApi): IChapterPersistence {
        return ChapterPersistence(restApi)
    }

    @Provides
    fun provideSearchPersistence(restApi: RestApi): ISearchPersistence {
        return SearchPersistence(restApi)
    }

    @Provides
    fun provideQuranPersistence(restApi: RestApi): IQuranPersistence {
        return QuranPersistence(restApi)
    }
}