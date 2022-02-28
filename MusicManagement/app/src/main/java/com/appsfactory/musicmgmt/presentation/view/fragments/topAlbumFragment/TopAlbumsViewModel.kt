package com.appsfactory.musicmgmt.presentation.view.fragments.topAlbumFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.common.ResultModel.Success
import com.appsfactory.musicmgmt.domain.usecases.GetTopAlbumListUsecase
import com.appsfactory.musicmgmt.presentation.uiModels.AlbumUiModel
import kotlinx.coroutines.launch


class TopAlbumsViewModel(private val getTopAlbumListUsecase: GetTopAlbumListUsecase) : ViewModel() {

    private val _searchArtistList = MutableLiveData<ResultModel<ArrayList<AlbumUiModel>>>()
    val searchArtistList: MutableLiveData<ResultModel<ArrayList<AlbumUiModel>>> = _searchArtistList

    fun getTopAlbumList(artistName: String) {

        _searchArtistList.postValue(ResultModel.Loading())
        viewModelScope.launch {
            getTopAlbumListUsecase.invoke(artistName).collect {
                when (it) {
                    is ResultModel.Loading -> _searchArtistList.postValue(ResultModel.Loading())
                    is ResultModel.Error -> {
                        _searchArtistList.postValue(ResultModel.Error(it.message.toString()))
                    }
                    is Success -> {
                        _searchArtistList.postValue(Success(it.data!!))
                    }
                }
            }
        }
    }
}


