package com.appsfactory.musicmgmt.presentation.view.fragments.searchFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsfactory.musicmgmt.domain.usecases.GetArtistListUsecase

@Suppress("UNCHECKED_CAST")
class SearchViewModelProviderFactory(private val getArtistListUsecase: GetArtistListUsecase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(getArtistListUsecase) as T
    }
}