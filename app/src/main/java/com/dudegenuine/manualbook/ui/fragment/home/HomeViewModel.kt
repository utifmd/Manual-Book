package com.dudegenuine.manualbook.ui.fragment.home

import android.util.Log
import com.dudegenuine.repos.network.Resource
import com.dudegenuine.chapter.GetChapters
import com.dudegenuine.domain.Chapter
import com.dudegenuine.manualbook.feature.di.component.ManualBookComponent
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * Manual Book created by utifmd on 20/06/21.
 */
class HomeViewModel: BaseViewModel() {
    private val TAG: String = javaClass.simpleName
    @Inject
    lateinit var getChapters: GetChapters

    fun loadChapter() {
        getChapters.getData(mapOf( "language" to "id" ))
        /*getChapters.getData(object: DisposableObserver<List<Chapter>>() {
            override fun onNext(t: List<Chapter>) {
                Log.d(TAG, "onNext: triggered")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError: ${e.localizedMessage}")
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete: triggered")
            }

        }, mapOf( "language" to "id" ))*/
    }

    init { ManualBookComponent.createComponent().inject(this) }
}
