package com.dudegenuine.manualbook.ui.fragment.verse

import android.os.Build
import android.text.Html
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
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

    private val _verse = MediatorLiveData<Resource<Verse>>()
    val verse = MutableLiveData<Resource<String>>()

    fun verse(verseNumber: String): LiveData<Resource<Verse>> {
        loadVerse(verseNumber)

        return _verse
    }

    private fun loadVerse(verseNumber: String) = viewModelScope.launch(Dispatchers.Main) {
        _verse.removeSource(verseState)

        withContext(Dispatchers.IO){
            verseState = getVerse(verseNumber, verseParam())
        }

        _verse.addSource(verseState){
            _verse.value = it

            verse.postValue(Resource.onSuccess("it"))

            if(it.status == Resource.Status.ERROR){
                _snackPopError.value = Event(R.string.msg_error)
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

        verse.postValue(Resource.onLoading())
    }
}
