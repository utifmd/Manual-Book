package com.dudegenuine.manualbook.ui.fragment.search

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dudegenuine.domain.Search
import com.dudegenuine.local.model.common.Event
import com.dudegenuine.manualbook.R
import com.dudegenuine.manualbook.ui.extention.BaseViewModel
import com.dudegenuine.repos.network.Resource
import com.dudegenuine.search.GetSearches
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Manual Book created by utifmd on 25/06/21.
 */
class SearchViewModel: BaseViewModel() {
    private val TAG: String = javaClass.simpleName
    @Inject
    lateinit var getSearches: GetSearches

    private var searchState: LiveData<Resource<List<Search>>> = MutableLiveData()

    private val _searches = MediatorLiveData<Resource<List<Search>>>()
    val searches: LiveData<Resource<List<Search>>> get() = _searches

    private fun loadSearches(query: String) = viewModelScope.launch(Dispatchers.Main) {
        _searches.removeSource(searchState)

        withContext(Dispatchers.IO){
            searchState = getSearches(searchParam(query = query))
        }

        _searches.addSource(searchState) {
            _searches.value = it

            if (it.status == Resource.Status.ERROR)
                _snackPop.value = Event(it.message ?: R.string.msg_error.toString())
        }
    }

    /*
    * Listener
    * */

    val onSearching: (String) -> Unit = {
        Log.d(TAG, "onSearching: $it")
        loadSearches(it)
    }

    val onBackSelected: (View) -> Unit = {
        navigateUp()
    }

    init {
        dependency().inject(this)
    }
}