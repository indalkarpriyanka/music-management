package com.appsfactory.musicmgmt.presentation.view.fragments.albumDetailsFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsfactory.musicmgmt.domain.usecases.GetAlbumDetailsFromDbUsecase
import com.appsfactory.musicmgmt.domain.usecases.GetAlbumDetailsUsecase
import com.appsfactory.musicmgmt.domain.usecases.RemoveAlbumFromDbUsecase
import com.appsfactory.musicmgmt.domain.usecases.SaveAlbumDetailsUseCase

@Suppress("UNCHECKED_CAST")
class AlbumDetailsViewModelProviderFactory(
    private val getAlbumDetailsUsecase: GetAlbumDetailsUsecase,
    private val saveAlbumDetailsUseCase: SaveAlbumDetailsUseCase,
    private val getAlbumDetailsFromDbUsecase: GetAlbumDetailsFromDbUsecase,
    private val removeAlbumFromDbUsecase: RemoveAlbumFromDbUsecase
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumDetailsViewModel(
            getAlbumDetailsUsecase,
            saveAlbumDetailsUseCase,
            getAlbumDetailsFromDbUsecase,
            removeAlbumFromDbUsecase
        ) as T
    }
}