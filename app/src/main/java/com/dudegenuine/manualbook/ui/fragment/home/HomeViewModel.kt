package com.dudegenuine.manualbook.ui.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dudegenuine.repos.network.Resource
import com.dudegenuine.chapter.GetChapters
import com.dudegenuine.domain.Chapter
import com.dudegenuine.local.model.common.Event
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.feature.di.component.ManualBookComponent
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.manualbook.ui.fragment.detail.FragmentDetail
import javax.inject.Inject

/**
 * Manual Book created by utifmd on 20/06/21.
 */
class HomeViewModel: BaseViewModel() {
    private val TAG: String = javaClass.simpleName
    @Inject
    lateinit var getChapters: GetChapters

    private val _chapters = MediatorLiveData<Resource<List<Chapter>>>()
    private var chaptersState: LiveData<Resource<List<Chapter>>> = MutableLiveData()

    init {
        dependency().inject(this)

        loadChapters()
    }

    private fun loadChapters() {
        _chapters.removeSource(chaptersState)

        chaptersState = getChapters(mapOf("language" to "id"))

        _chapters.addSource(chaptersState){
            _chapters.value = it

            if (it.status == Resource.Status.ERROR) {
                Log.d(TAG, "loadChapters: ${it.throwable?.localizedMessage}")
                _snackPopError.value = Event(R.string.msg_error)
            }
        }
    }

    val chapters get(): LiveData<Resource<List<Chapter>>> = _chapters

    /*
    * Listener
    * */

    fun onChaptersRefresh(){
        loadChapters()
    }

    fun onChapterItemSelected(chapter: Chapter){
        Log.d(TAG, "onChapterItemSelected: ${chapter.id}")

//        val direction =
//        navigate()
    }
}
