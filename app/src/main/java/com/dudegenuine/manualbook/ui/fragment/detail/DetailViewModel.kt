package com.dudegenuine.manualbook.ui.fragment.detail

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dudegenuine.chapter.GetChapterInfo
import com.dudegenuine.domain.Chapter
import com.dudegenuine.local.model.common.Event
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.repos.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Manual Book created by utifmd on 25/06/21.
 */
class DetailViewModel: BaseViewModel() {
    private val TAG: String = javaClass.simpleName
    @Inject
    lateinit var getChapterInfo: GetChapterInfo

    private var chapterInfoState: LiveData<Resource<Chapter>> = MutableLiveData()

    val chapterInfoResource = MediatorLiveData<Resource<Chapter>>()
    val chapterInfo: (Chapter) -> LiveData<Resource<Chapter>> = {
        loadChapterInfo(it)

        chapterInfoResource
    }

    /*
    * Request
    * */

    private fun loadChapterInfo(chapter: Chapter) = viewModelScope.launch(Dispatchers.Main) {
        chapterInfoResource.removeSource(chapterInfoState)

        withContext(Dispatchers.IO){
            chapterInfoState = getChapterInfo(chapter)
        }

        chapterInfoResource.addSource(chapterInfoState){
            chapterInfoResource.value = it

            if(it.status == Resource.Status.ERROR){
                _snackPop.value = Event(it.message ?: R.string.msg_error.toString())
            }
        }
    }

    /*
    * Listener
    * */

    fun onBackSelected(view: View){
        Log.d(TAG, "onBack: triggered ${view.id}.")

        navigateUp()
    }

    init { dependency().inject(this) }
}