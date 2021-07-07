package com.dudegenuine.manualbook.ui.fragment.quran

import android.view.View
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dudegenuine.domain.Quran
import com.dudegenuine.local.model.common.Event
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.manualbook.ui.fragment.quran.views.QuranAdapter
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
    lateinit var quranListener: QuranAdapter.Listener
    lateinit var quranCallback: QuranAdapter.Listener.Callback

    var quranState: Flow<PagingData<Quran>>? = null

    val quran: (Int, Int, Int) -> Flow<PagingData<Quran>> = { chapterId, startPage, endPage ->
        loadQuran(chapterId, startPage, endPage)
    }

    private fun loadQuran(chapterId: Int, startPage: Int, endPage: Int): Flow<PagingData<Quran>> {
        val lastResult = quranState
        if (lastResult != null) {
            return lastResult
        }

        val newResult: Flow<PagingData<Quran>> = getQuran( mapOf(
                "chapter_number" to chapterId,
                "start_page" to startPage,
                "finish_page" to endPage )).cachedIn(viewModelScope)

        quranState = newResult

        return newResult
    }

    fun playButtonSelect(view: View) =
        quranListener.onEventPlaySelected(view)

    fun onAudioPlayed() = quranCallback.onAudioPlayed().apply {
        _snackPop.value = Event(R.string.msg_audio_completion)
    }

    init { dependency().inject(this) }
}