package com.appsfactory.musicmgmt.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.data.local.dao.AlbumEntity
import com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels.AlbumDetailResponseModel
import com.appsfactory.musicmgmt.data.repository.Repository
import com.appsfactory.musicmgmt.domain.usecases.GetAlbumDetailsUsecase
import kotlinx.coroutines.launch
import retrofit2.Response

class AlbumDetailsViewModel(private val getAlbumDetailsUsecase: GetAlbumDetailsUsecase) :
    ViewModel() {

    private val _albumDetailResponseModel = MutableLiveData<ResultModel<AlbumDetailResponseModel>>()
    val albumDetailResponseModel: LiveData<ResultModel<AlbumDetailResponseModel>> =
        _albumDetailResponseModel

    fun getAlbumDetails(mbid: String?, artistName: String, albumName: String) {

        _albumDetailResponseModel.postValue(ResultModel.Loading())
        viewModelScope.launch {
            getAlbumDetailsUsecase.invoke(mbid, artistName, albumName).collect {
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


    /* suspend fun addAlbumToDatabase(albumEntity: AlbumEntity) {
         repository.insertAlbum(albumEntity)
     }*/

}