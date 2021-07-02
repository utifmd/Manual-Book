package com.dudegenuine.manualbook.ui.fragment.quran

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dudegenuine.domain.Quran
import com.dudegenuine.manualbook.NavGraphHomeFeatureDirections
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.quran.GetQuran
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Manual Book created by utifmd on 27/06/21.
 */
class QuranViewModel: BaseViewModel() {
    private val TAG: String = javaClass.simpleName
    @Inject
    lateinit var getQuran: GetQuran

    private var verseState: Flow<PagingData<Quran>>? = null

    fun verses(startPage: Int, endPage: Int): Flow<PagingData<Quran>> {
        val lastResult = verseState
        if (lastResult != null) {
            return lastResult
        }

        val newResult: Flow<PagingData<Quran>> =
            getQuran(mapOf(
                "start_page" to startPage,
                "finish_page" to endPage
            )).cachedIn(viewModelScope)

        verseState = newResult

        return newResult
    }

    init { dependency().inject(this) }

    /*
    * Listener
    * */

    fun onItemSelected(quran: Quran) {
        Log.d(TAG, "onItemSelected: ${quran.verseKey}")

        // TODO: 02/07/21 navigate to detail or re mapping quran requests
    }
}