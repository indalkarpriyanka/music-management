package com.appsfactory.musicmgmt.common

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.appsfactory.musicmgmt.presentation.viewModels.AlbumDetailsViewModel
import com.appsfactory.musicmgmt.presentation.viewModels.SearchViewModel
import com.appsfactory.musicmgmt.presentation.viewModels.TopAlbumsViewModel

class ActivityCompositeRoot(
    activity: AppCompatActivity,
    applicationCompositeRoot: ApplicationCompositeRoot
) {
    val searchViewModel by lazy {
        ViewModelProvider(
            activity,
            applicationCompositeRoot.searchViewModelProviderFactory
        ).get(SearchViewModel::class.java)
    }

    val topAlbumsViewModel by lazy {
        ViewModelProvider(
            activity,
            applicationCompositeRoot.topAlbumsViewModelProviderFactory
        ).get(TopAlbumsViewModel::class.java)
    }
    val albumDetailsViewModel by lazy {
        ViewModelProvider(
            activity,
            applicationCompositeRoot.albumDetailViewModelProviderFactory
        ).get(AlbumDetailsViewModel::class.java)
    }
}