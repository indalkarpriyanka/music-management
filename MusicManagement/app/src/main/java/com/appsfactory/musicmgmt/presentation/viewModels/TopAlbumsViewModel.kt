package com.appsfactory.musicmgmt.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.common.ResultModel.Success
import com.appsfactory.musicmgmt.data.remote.network.models.topAlbumsModels.TopAlbumsResponseModel
import com.appsfactory.musicmgmt.domain.usecases.GetTopAlbumListUsecase
import com.appsfactory.musicmgmt.presentation.uiModels.AlbumUiModel
import kotlinx.coroutines.launch
import retrofit2.Response

class TopAlbumsViewModel(private val getTopAlbumListUsecase: GetTopAlbumListUsecase) : ViewModel() {

    val searchArtistList = MutableLiveData<ResultModel<ArrayList<AlbumUiModel>>>()

    fun getTopAlbumList(artistName: String) {

        searchArtistList.postValue(ResultModel.Loading())
        viewModelScope.launch {
            getTopAlbumListUsecase.invoke(artistName).collect {
                when (it) {
                    is ResultModel.Loading -> searchArtistList.postValue(ResultModel.Loading())
                    is ResultModel.Error -> {
                        searchArtistList.postValue(ResultModel.Error(it.message.toString()))
                    }
                    is Success -> {
                        searchArtistList.postValue(Success(it.data!!))
                    }
                }
            }
        }
    }
}

/* suspend fun getTopAlbumList(artistName: String) {
     searchArtistList.postValue(ResultModel.Loading())
     val response = repository.getTopAlbumList(artistName)
     searchArtistList.postValue(handleResponseData(response))
 }

 private fun handleResponseData(artistList: Response<TopAlbumsResponseModel>): ResultModel<TopAlbumsResponseModel> {
     if (artistList.isSuccessful) {
         artistList.body()?.let {
             return Success(artistList.body()!!)
         }
     }
     return ResultModel.Error(artistList.message())
 }*/
