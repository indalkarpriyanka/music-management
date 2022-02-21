package com.appsfactory.musicmgmt.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsfactory.musicmgmt.domain.usecases.GetAlbumDetailsUsecase
import com.appsfactory.musicmgmt.domain.usecases.SaveAlbumDetailsUseCase

@Suppress("UNCHECKED_CAST")
class AlbumDetailsViewModelProviderFactory(
    private val getAlbumDetailsUsecase: GetAlbumDetailsUsecase,
    private val saveAlbumDetailsUseCase: SaveAlbumDetailsUseCase
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumDetailsViewModel(getAlbumDetailsUsecase, saveAlbumDetailsUseCase) as T
    }
}