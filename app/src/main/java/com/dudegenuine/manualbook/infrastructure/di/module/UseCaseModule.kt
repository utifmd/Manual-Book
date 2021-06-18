package com.dudegenuine.manualbook.infrastructure.di.module

import com.dudegenuine.data.remote.persistance.repository.ChapterRepository
import com.dudegenuine.usecases.GetChapters
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

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