package com.appsfactory.musicmgmt.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsfactory.musicmgmt.domain.usecases.GetAlbumDetailsUsecase

@Suppress("UNCHECKED_CAST")
class AlbumDetailsViewModelProviderFactory(private val getAlbumDetailsUsecase: GetAlbumDetailsUsecase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumDetailsViewModel(getAlbumDetailsUsecase) as T
    }
}