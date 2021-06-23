package com.dudegenuine.chapter

import androidx.lifecycle.LiveData
import com.dudegenuine.domain.Chapter
import com.dudegenuine.repos.domain.chapter.ChapterRepository
import com.dudegenuine.repos.network.Resource
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class GetChapters(
    private var repository: ChapterRepository,
    private var compositeDisposable: CompositeDisposable,
    private var scheduler: Scheduler ) {

    fun getData(param: Map<String, String>): Resource<List<Chapter>>{
        return repository.getChapters(param)
    }

    /*fun getData(observer: DisposableObserver<List<Chapter>>, param: Map<String, String>) {
        val observable = repository.getChapters(param)
            .subscribeOn(Schedulers.newThread())
            .observeOn(scheduler)

        compositeDisposable.add(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }*/
}