package com.appsfactory.musicmgmt.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsfactory.musicmgmt.domain.usecases.GetMyAlbumListUsecase

@Suppress("UNCHECKED_CAST")
class MyAlbumListViewModelProviderFactory(private val getMyAlbumListUsecase: GetMyAlbumListUsecase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyAlbumListViewModel(getMyAlbumListUsecase) as T
    }
}