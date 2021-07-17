package com.dudegenuine.manualbook.ui.fragment.verse

import android.os.Build
import android.text.Html
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.dudegenuine.domain.Chapter
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
@RequiresApi(Build.VERSION_CODES.N)
class VerseViewModel(savedStateHandle: SavedStateHandle): BaseViewModel() {
    private val TAG: String = javaClass.simpleName

    @Inject lateinit var getVerse: GetVerse

    private var verseState: LiveData<Resource<Verse>> = MutableLiveData()
    private val _verse = MediatorLiveData<Resource<Verse>>()
    val verse: LiveData<Resource<Verse>> get() = _verse

    init {
        dependency().inject(this)

        val quran: Quran? = savedStateHandle[Quran.TAG_KEY]

        quran?.let { loadVerse(it.verseKey) }
    }

    private fun loadVerse(verseNumber: String) = viewModelScope.launch(Dispatchers.Main) {
        _verse.removeSource(verseState)

        withContext(Dispatchers.IO){
            verseState = getVerse(verseNumber, verseParam())
        }

        _verse.addSource(verseState) {
            when(it.status){
                Resource.Status.SUCCESS -> it.data?.let { verse ->
                    _verse.value = Resource.onSuccess( Verse( verse,
                        Html.fromHtml(verse.translationText, Html.FROM_HTML_MODE_LEGACY).toString()
                    ))
                }
                Resource.Status.ERROR ->
                    _snackPop.value = Event(it.message ?: R.string.msg_error.toString())
                Resource.Status.LOADING ->
                    _verse.value = Resource.onLoading()
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
