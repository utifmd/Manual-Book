package com.dudegenuine.usecases

import com.dudegenuine.domain.Chapter
import com.dudegenuine.repository.chapter.ChapterRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class GetChapters(
    private var repository: ChapterRepository,
    private var compositeDisposable: CompositeDisposable,
    private var scheduler: Scheduler ) {

    fun getData(observer: DisposableObserver<List<Chapter>>, param: Map<String, String>) {
        val observable = repository.getChapters(param)
            .subscribeOn(Schedulers.newThread())
            .observeOn(scheduler)

        compositeDisposable.add(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }
}
