package com.dudegenuine.manualbook.feature.di.module

import androidx.annotation.MainThread
import com.dudegenuine.chapter.GetChapterInfo
import com.dudegenuine.chapter.GetChapters
import com.dudegenuine.quran.GetQuran
import com.dudegenuine.repos.domain.chapter.ChapterRepository
import com.dudegenuine.repos.domain.quran.QuranRepository
import com.dudegenuine.repos.domain.search.SearchRepository
import com.dudegenuine.repos.domain.verse.VerseRepository
import com.dudegenuine.search.GetSearches
import com.dudegenuine.verse.GetVerse
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * Manual Book created by utifmd on 17/06/21.
 */
@Module
class UseCaseModule {
    @Provides
    fun provideChaptersUseCase(chapterRepository: ChapterRepository): GetChapters =
        GetChapters(chapterRepository) //, compositeDisposable: CompositeDisposable, mainThread: Scheduler

    @Provides
    fun provideChapterInfoUseCase(chapterRepository: ChapterRepository): GetChapterInfo =
        GetChapterInfo(chapterRepository)

    @Provides
    fun provideSearchUseCase(repository: SearchRepository): GetSearches =
        GetSearches(repository)

    @Provides
    fun provideQuranUseCase(repository: QuranRepository): GetQuran =
        GetQuran(repository)

    @Provides
    fun provideVerseUseCase(repository: VerseRepository): GetVerse =
        GetVerse(repository)
}