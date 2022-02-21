package com.appsfactory.musicmgmt.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.data.local.dao.AlbumEntity
import com.appsfactory.musicmgmt.domain.usecases.GetMyAlbumListUsecase
import com.appsfactory.musicmgmt.presentation.uiModels.AlbumUiModel
import kotlinx.coroutines.launch

class MyAlbumListViewModel(private val getMyAlbumListUsecase: GetMyAlbumListUsecase) : ViewModel() {

    var myAlbumList = MutableLiveData<ResultModel<ArrayList<AlbumUiModel>>>()

    fun getMyAlbumList() {
        viewModelScope.launch {
            getMyAlbumListUsecase.invoke().collect {
                if (it.data?.isNotEmpty() == true) {
                    myAlbumList.postValue(ResultModel.Success(it.data))
                }
            }
        }
    }

}