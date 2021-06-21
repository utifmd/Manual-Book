package com.dudegenuine.manualbook.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import com.dudegenuine.domain.Chapter
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.feature.di.component.ManualBookComponent
import io.reactivex.observers.DisposableObserver

class MainActivity : AppCompatActivity(){
    val TAG: String = javaClass.simpleName
    /*@Inject
    lateinit var getChapters: GetChapters*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ManualBookComponent.createComponent().inject(this)

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

    override fun onDestroy() {
        super.onDestroy()

        // getChapters.dispose()
    }
}