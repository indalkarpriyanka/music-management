package com.appsfactory.musicmgmt.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.data.local.dao.AlbumEntity
import com.appsfactory.musicmgmt.domain.usecases.GetAlbumDetailsFromDbUsecase
import com.appsfactory.musicmgmt.domain.usecases.GetAlbumDetailsUsecase
import com.appsfactory.musicmgmt.domain.usecases.RemoveAlbumFromDbUsecase
import com.appsfactory.musicmgmt.domain.usecases.SaveAlbumDetailsUseCase
import com.appsfactory.musicmgmt.presentation.uiModels.AlbumUiModel
import kotlinx.coroutines.launch

class AlbumDetailsViewModel(
    private val getAlbumDetailsUsecase: GetAlbumDetailsUsecase,
    private val saveAlbumDetailsUseCase: SaveAlbumDetailsUseCase,
    private val getAlbumDetailsFromDbUsecase: GetAlbumDetailsFromDbUsecase,
    private val removeAlbumFromDbUsecase: RemoveAlbumFromDbUsecase
) :
    ViewModel() {

    private val _albumDetailResponseModel = MutableLiveData<ResultModel<AlbumEntity>>()
    val albumDetailResponseModel: LiveData<ResultModel<AlbumEntity>> =
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

    fun getAlbumDetailsFRomDb(id: Int) {
        viewModelScope.launch {
            var albumEntity = getAlbumDetailsFromDbUsecase.invoke(id)
            _albumDetailResponseModel.postValue(ResultModel.Success(albumEntity))
        }
    }

    fun addAlbumToDatabase(albumEntity: AlbumEntity) {
        viewModelScope.launch { saveAlbumDetailsUseCase.invoke(albumEntity) }
    }

    fun removeAlbum(id: Int) {
        viewModelScope.launch {
            removeAlbumFromDbUsecase.invoke(id)
        }
    }
}