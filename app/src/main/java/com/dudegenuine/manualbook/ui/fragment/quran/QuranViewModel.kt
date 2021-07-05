package com.dudegenuine.manualbook.ui.fragment.quran

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dudegenuine.domain.Quran
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.quran.GetQuran
import com.dudegenuine.repos.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Manual Book created by utifmd on 27/06/21.
 */
class QuranViewModel: BaseViewModel() {
    private val TAG: String = javaClass.simpleName
    @Inject
    lateinit var getQuran: GetQuran

    private var quranState: Flow<PagingData<Quran>>? = null
    // private var chapterState: Chapter? = null

    val quran = MutableLiveData<Resource<String>>()

    fun verses(startPage: Int, endPage: Int): Flow<PagingData<Quran>> {
        val lastResult = quranState
        if (lastResult != null) {
            return lastResult
        }

        val newResult: Flow<PagingData<Quran>> =
            getQuran( mapOf(
                "start_page" to startPage,
                "finish_page" to endPage
            )).cachedIn(viewModelScope)

        quranState = newResult.apply {
            quran.postValue(Resource.onSuccess("this"))
        }

        return newResult
    }

    init {
        dependency().inject(this)

        quran.postValue(Resource.onLoading())
    }

    /*
    * Listener
    * */

    /*fun onItemSelected(quran: Quran) {
        Log.d(TAG, "onItemSelected: ${quran.verseKey}")

        navigate( NavGraphHomeFeatureDirections.actionGlobalToVerse(
            quran, chapter
        ), null)
    }*/
}