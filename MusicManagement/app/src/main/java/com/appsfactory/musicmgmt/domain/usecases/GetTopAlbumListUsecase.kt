package com.appsfactory.musicmgmt.domain.usecases

import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.data.remote.network.models.topAlbumsModels.TopAlbumsResponseModel
import com.appsfactory.musicmgmt.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetTopAlbumListUsecase(private val repository: Repository) {

    operator fun invoke(artistName: String): Flow<ResultModel<TopAlbumsResponseModel>> = flow {
        try {
            emit(ResultModel.Loading())
            val topAlbumsResponseModel = repository.getTopAlbumList(artistName)
            if (topAlbumsResponseModel.isSuccessful) {
                topAlbumsResponseModel.body()?.let {
                    emit(ResultModel.Success(it))
                }
            }
        } catch (e: HttpException) {
            emit(ResultModel.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(ResultModel.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}