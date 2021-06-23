package com.dudegenuine.manualbook.feature.di.module

import android.content.Context
import com.dudegenuine.manualbook.ManualBook
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Manual Book created by utifmd on 17/06/21.
 */
@Module
class AndroidModule {
    @Provides
    fun provideContext(): Context =
        ManualBook.creteInstance().applicationContext

    @Singleton @Provides
    fun provideCompositeDisposable(): CompositeDisposable =
        CompositeDisposable()

    fun provideMainThread(): Scheduler =
        AndroidSchedulers.mainThread()
}