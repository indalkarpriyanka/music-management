package com.appsfactory.musicmgmt.presentation.view.fragments.myMusicListFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.domain.usecases.GetMyAlbumListUsecase
import com.appsfactory.musicmgmt.presentation.uiModels.AlbumUiModel
import kotlinx.coroutines.launch

class MyAlbumListViewModel(private val getMyAlbumListUsecase: GetMyAlbumListUsecase) : ViewModel() {

    private val _myAlbumList = MutableLiveData<ResultModel<ArrayList<AlbumUiModel>?>>()
    val myAlbumList: MutableLiveData<ResultModel<ArrayList<AlbumUiModel>?>> = _myAlbumList

    fun getMyAlbumList() {
        viewModelScope.launch {
            getMyAlbumListUsecase.invoke().collect {
                _myAlbumList.postValue(ResultModel.Success(it.data))
            }
        }
    }
}