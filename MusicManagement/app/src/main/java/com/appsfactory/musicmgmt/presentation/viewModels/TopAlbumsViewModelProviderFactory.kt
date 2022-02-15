package com.appsfactory.musicmgmt.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsfactory.musicmgmt.domain.usecases.GetTopAlbumListUsecase

class TopAlbumsViewModelProviderFactory(private val getTopAlbumListUsecase: GetTopAlbumListUsecase):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopAlbumsViewModel(getTopAlbumListUsecase) as T
    }
}