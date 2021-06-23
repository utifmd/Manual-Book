package com.dudegenuine.manualbook.feature.di.module

import androidx.annotation.MainThread
import com.dudegenuine.chapter.GetChapters
import com.dudegenuine.repos.domain.chapter.ChapterRepository
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
    fun provideChaptersUseCase(
        chapterRepository: ChapterRepository //, compositeDisposable: CompositeDisposable, mainThread: Scheduler
    ): GetChapters {
        return GetChapters(chapterRepository)
    }
}