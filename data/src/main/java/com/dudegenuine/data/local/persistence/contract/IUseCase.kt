package com.dudegenuine.data.local.persistence.contract

import io.reactivex.observers.DisposableObserver

/**
 * Manual Book created by utifmd on 17/06/21.
 */
interface IUseCase {
    fun<T> getData(observer: DisposableObserver<T>, param: Map<String, String>)
    fun dispose()
}