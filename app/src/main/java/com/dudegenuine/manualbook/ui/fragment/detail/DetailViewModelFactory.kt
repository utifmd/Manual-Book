package com.dudegenuine.manualbook.ui.fragment.detail

import androidx.lifecycle.SavedStateHandle
import com.dudegenuine.manualbook.ui.extention.BaseViewModelFactory

/**
 * Manual Book created by utifmd on 13/07/21.
 */
class DetailViewModelFactory: BaseViewModelFactory.ViewModelAssistedFactory<DetailViewModel> {
    override fun create(savedStateHandle: SavedStateHandle): DetailViewModel {
        return DetailViewModel(savedStateHandle)
    }
}