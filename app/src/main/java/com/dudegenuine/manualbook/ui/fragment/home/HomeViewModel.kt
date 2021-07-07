package com.dudegenuine.manualbook.ui.fragment.home

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.dudegenuine.repos.network.Resource
import com.dudegenuine.chapter.GetChapters
import com.dudegenuine.domain.Chapter
import com.dudegenuine.local.model.common.Event
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Manual Book created by utifmd on 20/06/21.
 */
class HomeViewModel: BaseViewModel() {
    private val TAG: String = javaClass.simpleName
    @Inject
    lateinit var getChapters: GetChapters

    private var chaptersState: LiveData<Resource<List<Chapter>>> = MutableLiveData()

    private val _chapters = MediatorLiveData<Resource<List<Chapter>>>()
    val chapters get(): LiveData<Resource<List<Chapter>>> = _chapters

    init {
        dependency().inject(this)

        loadChapters()
    }

    private fun loadChapters() = viewModelScope.launch(Dispatchers.Main) {
        _chapters.removeSource(chaptersState)

        withContext(Dispatchers.IO){
            chaptersState = getChapters(chaptersParam())
        }

        _chapters.addSource(chaptersState){
            _chapters.value = it

            if (it.status == Resource.Status.ERROR) {
                Log.d(TAG, "loadChapters: ${it.throwable?.localizedMessage}")
                _snackPop.value = Event(R.string.msg_error)
            }
        }
    }

    /*
    * Listener
    * */

    fun onChapterItemSelected(cardView: View, chapter: Chapter) = navigate (
        FragmentHomeDirections.actionGlobalToDetail(chapter),
        FragmentNavigatorExtras(
            cardView to cardView.resources.getString(R.string.transition_name_dest)
        )
    )

    fun onChaptersRefresh(){
        loadChapters()
    }
}
