package com.dudegenuine.manualbook.feature.di.module

import com.dudegenuine.repository.chapter.ChapterRepository
import com.dudegenuine.usecases.GetChapters
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * Manual Book created by utifmd on 17/06/21.
 */
@Module
class UseCaseModule {
    @Provides
    fun provideChaptersUseCase(
        chapterRepository: ChapterRepository,
        compositeDisposable: CompositeDisposable): GetChapters {
        return GetChapters(chapterRepository, compositeDisposable, AndroidSchedulers.mainThread())
    }
}