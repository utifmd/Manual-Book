package com.dudegenuine.manualbook.ui.fragment.verse

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.dudegenuine.domain.Quran
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
class VerseViewModel(savedStateHandle: SavedStateHandle): BaseViewModel() {
    private val TAG: String = javaClass.simpleName

    @Inject lateinit var getVerse: GetVerse

    private var verseState: LiveData<Resource<Verse>> = MutableLiveData()
    private val _verse = MediatorLiveData<Resource<Verse>>()
    val verse: LiveData<Resource<Verse>> get() = _verse

    init {
        dependency().inject(this)

        savedStateHandle.get<Quran>(Quran.TAG_KEY)?.let {
            loadVerse(it.verseKey)
        }
    }

    private fun loadVerse(verseNumber: String) = viewModelScope.launch(Dispatchers.Main) {
        _verse.removeSource(verseState)

        withContext(Dispatchers.IO){
            verseState = getVerse(verseNumber, verseParam())
        }

        _verse.addSource(verseState){
            _verse.value = it

            if(it.status == Resource.Status.ERROR){
                _snackPop.value = Event(it.message ?: R.string.msg_error.toString())
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
}
