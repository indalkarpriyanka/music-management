package com.appsfactory.musicmgmt.common


import android.content.Context
import com.appsfactory.musicmgmt.data.remote.network.Api
import com.appsfactory.musicmgmt.data.remote.network.MusicMgmtService
import com.appsfactory.musicmgmt.data.local.dao.AlbumDatabase
import com.appsfactory.musicmgmt.data.repository.Repository
import com.appsfactory.musicmgmt.domain.usecases.*
import com.appsfactory.musicmgmt.presentation.viewModels.SearchViewModelProviderFactory
import com.appsfactory.musicmgmt.presentation.viewModels.TopAlbumsViewModelProviderFactory
import com.appsfactory.musicmgmt.presentation.viewModels.AlbumDetailsViewModelProviderFactory
import com.appsfactory.musicmgmt.presentation.viewModels.MyAlbumListViewModelProviderFactory

class ApplicationCompositeRoot(context: Context) {
    private val retrofitService: MusicMgmtService by lazy {
        Api.retrofit.create(MusicMgmtService::class.java)
    }
    private val database by lazy {
        AlbumDatabase.getDatabase(context)
    }
    private val repository by lazy {
        Repository(retrofitService, database.albumDao())
    }

    private val artistListUsecase by lazy {
        GetArtistListUsecase(repository)
    }

    private val topAlbumListUsecase by lazy {
        GetTopAlbumListUsecase(repository)
    }
    private val getAlbumDetailsUsecase by lazy {
        GetAlbumDetailsUsecase(repository)
    }
    private val getMyAlbumListUsecase by lazy {
        GetMyAlbumListUsecase(repository)
    }

    private val saveAlbumDetailsUseCase by lazy {
        SaveAlbumDetailsUseCase(repository)
    }

    val albumDetailViewModelProviderFactory by lazy {
        AlbumDetailsViewModelProviderFactory(getAlbumDetailsUsecase, saveAlbumDetailsUseCase)
    }
    val searchViewModelProviderFactory by lazy {
        SearchViewModelProviderFactory(artistListUsecase)
    }
    val topAlbumsViewModelProviderFactory by lazy {
        TopAlbumsViewModelProviderFactory(topAlbumListUsecase)
    }
    val myAlbumsListViewModelProviderFactory by lazy {
        MyAlbumListViewModelProviderFactory(getMyAlbumListUsecase)
    }
}