package com.dudegenuine.manualbook.ui.fragment.verse

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dudegenuine.domain.Verse
import com.dudegenuine.local.model.common.Event
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.repos.network.Resource
import com.dudegenuine.verse.GetVerse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Manual Book created by utifmd on 03/07/21.
 */
class VerseViewModel: BaseViewModel() {
    private val TAG: String = javaClass.simpleName
    @Inject
    lateinit var getVerse: GetVerse

    private var verseState: LiveData<Resource<Verse>> = MutableLiveData()

    val verseResource = MediatorLiveData<Resource<Verse>>()
    fun verse(verseNumber: String): LiveData<Resource<Verse>> {
        loadVerse(verseNumber)

        return verseResource
    }

    private fun loadVerse(verseNumber: String) = viewModelScope.launch(Dispatchers.Main) {
        verseResource.removeSource(verseState)

        withContext(Dispatchers.IO){
            verseState = getVerse(verseNumber, verseParam())
        }

        verseResource.addSource(verseState){
            verseResource.value = it

            if(it.status == Resource.Status.ERROR){
                _snackPop.value = Event(R.string.msg_error)
            }
        }
    }

    /*
    * Listener
    * */

    fun onBackSelected(view: View){
        Log.d(TAG, "onBackSelected: ${view.id}")

        navigateUp()
    }

    init {
        dependency().inject(this)
    }
}
