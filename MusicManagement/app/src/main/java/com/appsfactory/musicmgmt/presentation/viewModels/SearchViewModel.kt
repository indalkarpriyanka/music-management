package com.appsfactory.musicmgmt.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.data.remote.network.models.artistSearchModels.SearchArtistResponse
import com.appsfactory.musicmgmt.domain.usecases.GetArtistListUsecase
import kotlinx.coroutines.launch

class SearchViewModel(private val getArtistListUsecase: GetArtistListUsecase) : ViewModel() {

    val searchArtistList = MutableLiveData<ResultModel<SearchArtistResponse>>()

    fun getArtistList(searchText: String) {
        searchArtistList.postValue(ResultModel.Loading())
        viewModelScope.launch {
            getArtistListUsecase.invoke(searchText).collect {
                when (it) {
                    is ResultModel.Loading ->searchArtistList.postValue(ResultModel.Loading())
                    is ResultModel.Error -> {
                        searchArtistList.postValue(ResultModel.Error(it.message.toString()))
                    }
                    is ResultModel.Success -> {
                        ResultModel.Success(it.data)
                            ?.let { it1 -> searchArtistList.postValue(ResultModel.Success(it.data!!)) }
                    }
                }

            }

        }
    }
}