package com.appsfactory.musicmgmt.presentation.view.fragments.topAlbumFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsfactory.musicmgmt.domain.usecases.GetTopAlbumListUsecase

@Suppress("UNCHECKED_CAST")
class TopAlbumsViewModelProviderFactory(private val getTopAlbumListUsecase: GetTopAlbumListUsecase):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopAlbumsViewModel(getTopAlbumListUsecase) as T
    }
}