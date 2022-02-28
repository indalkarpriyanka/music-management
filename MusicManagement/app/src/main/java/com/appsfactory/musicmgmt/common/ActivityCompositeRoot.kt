package com.appsfactory.musicmgmt.common

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.appsfactory.musicmgmt.presentation.view.fragments.albumDetailsFragment.AlbumDetailsViewModel
import com.appsfactory.musicmgmt.presentation.view.fragments.myMusicListFragment.MyAlbumListViewModel
import com.appsfactory.musicmgmt.presentation.view.fragments.searchFragment.SearchViewModel
import com.appsfactory.musicmgmt.presentation.view.fragments.topAlbumFragment.TopAlbumsViewModel

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

    val myAlbumsListViewModel by lazy {
        ViewModelProvider(
            activity,
            applicationCompositeRoot.myAlbumsListViewModelProviderFactory
        ).get(MyAlbumListViewModel::class.java)
    }
}