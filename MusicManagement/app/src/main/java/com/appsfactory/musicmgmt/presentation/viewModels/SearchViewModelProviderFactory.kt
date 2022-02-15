package com.appsfactory.musicmgmt.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsfactory.musicmgmt.domain.usecases.GetArtistListUsecase

class SearchViewModelProviderFactory(private val getArtistListUsecase: GetArtistListUsecase):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(getArtistListUsecase) as T
    }
}