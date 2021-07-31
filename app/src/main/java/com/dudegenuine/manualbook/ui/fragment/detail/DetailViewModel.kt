package com.dudegenuine.manualbook.ui.fragment.detail

import android.os.Build
import android.text.Html
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.lifecycle.*
import com.dudegenuine.chapter.GetChapterInfo
import com.dudegenuine.domain.Chapter
import com.dudegenuine.local.model.common.Event
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.manualbook.ui.extention.BaseViewModelFactory
import com.dudegenuine.manualbook.ui.fragment.quran.views.QuranAdapter
import com.dudegenuine.repos.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Manual Book created by utifmd on 25/06/21.
 */
@RequiresApi(Build.VERSION_CODES.N)
class DetailViewModel(savedStateHandle: SavedStateHandle): BaseViewModel(), LifecycleObserver {
    private val TAG: String = javaClass.simpleName

    @Inject lateinit var getChapterInfo: GetChapterInfo

    private var chapterInfoState: LiveData<Resource<Chapter>> = MutableLiveData()

    private val _chapterInfo = MediatorLiveData<Resource<Chapter>>()
    val chapterInfo: LiveData<Resource<Chapter>> = _chapterInfo

    init {
        dependency().inject(this)

        val chapter: Chapter? = savedStateHandle[Chapter.TAG_KEY]

        chapter?.let { loadChapterInfo(it) }
    }

    /*
    * Request
    * */

    private fun loadChapterInfo(chapter: Chapter) = viewModelScope.launch(Dispatchers.Main) {
        _chapterInfo.removeSource(chapterInfoState)

        withContext(Dispatchers.IO){
            chapterInfoState = getChapterInfo(chapter)
        }

        _chapterInfo.addSource(chapterInfoState){
            when(it.status){
                Resource.Status.SUCCESS -> it.data?.let { cpt -> _chapterInfo.value = Resource.onSuccess( Chapter( cpt,
                    Html.fromHtml(cpt.infoText!!, FROM_HTML_MODE_LEGACY).toString(), cpt.infoShortText!!, cpt.infoSource!!
                )) }
                Resource.Status.ERROR -> _snackPop.value = Event(it.message ?: R.string.msg_error.toString())
                Resource.Status.LOADING -> _chapterInfo.value = Resource.onLoading()
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

    companion object DetailFactory: BaseViewModelFactory.ViewModelAssistedFactory<DetailViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): DetailViewModel {
            return DetailViewModel(savedStateHandle)
        }
    }
}