package com.dudegenuine.manualbook.ui.extention

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.dudegenuine.domain.Chapter
import com.dudegenuine.manualbook.ui.fragment.quran.views.QuranAdapter

/**
 * Manual Book created by utifmd on 13/07/21.
 */
class BaseViewModelFactory<out V: ViewModel>(
    private val viewModelFactory: ViewModelAssistedFactory<V>,
    owner: SavedStateRegistryOwner,
    bundle: Bundle? = null): AbstractSavedStateViewModelFactory(owner, bundle){

    override fun <T : ViewModel?> create( key: String, modelClass: Class<T>, handle: SavedStateHandle ): T {
        @Suppress("UNCHECKED_CAST")
        return viewModelFactory.create(handle) as T
    }

    interface ViewModelAssistedFactory<T> {
        fun create(savedStateHandle: SavedStateHandle): T
    }
}