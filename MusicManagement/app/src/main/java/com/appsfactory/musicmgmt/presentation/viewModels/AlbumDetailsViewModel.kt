package com.appsfactory.musicmgmt.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.data.local.dao.AlbumEntity
import com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels.AlbumDetailResponseModel
import com.appsfactory.musicmgmt.domain.usecases.GetAlbumDetailsUsecase
import com.appsfactory.musicmgmt.domain.usecases.SaveAlbumDetailsUseCase
import com.appsfactory.musicmgmt.presentation.uiModels.AlbumUiModel
import kotlinx.coroutines.launch

class AlbumDetailsViewModel(
    private val getAlbumDetailsUsecase: GetAlbumDetailsUsecase,
    private val saveAlbumDetailsUseCase: SaveAlbumDetailsUseCase
) :
    ViewModel() {

    private val _albumDetailResponseModel = MutableLiveData<ResultModel<AlbumDetailResponseModel>>()
    val albumDetailResponseModel: LiveData<ResultModel<AlbumDetailResponseModel>> =
        _albumDetailResponseModel

    fun getAlbumDetails(album: AlbumUiModel) {

        _albumDetailResponseModel.postValue(ResultModel.Loading())
        viewModelScope.launch {
            getAlbumDetailsUsecase.invoke(album).collect {
                when (it) {
                    is ResultModel.Loading -> _albumDetailResponseModel.postValue(ResultModel.Loading())
                    is ResultModel.Error -> {
                        _albumDetailResponseModel.postValue(ResultModel.Error(it.message.toString()))
                    }
                    is ResultModel.Success -> {

                        _albumDetailResponseModel.postValue(
                            ResultModel.Success(
                                it.data!!
                            )
                        )

                    }
                }
            }
        }
    }


    fun addAlbumToDatabase(albumEntity: AlbumEntity) {

        viewModelScope.launch { saveAlbumDetailsUseCase.invoke(albumEntity) }
    }
}