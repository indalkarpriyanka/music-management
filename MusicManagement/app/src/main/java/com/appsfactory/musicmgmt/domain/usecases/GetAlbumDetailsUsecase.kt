package com.appsfactory.musicmgmt.domain.usecases

import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.data.local.dao.AlbumDao
import com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels.AlbumDetailResponseModel
import com.appsfactory.musicmgmt.data.repository.Repository
import com.appsfactory.musicmgmt.presentation.uiModels.AlbumUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetAlbumDetailsUsecase(private val repository: Repository) {

    operator fun invoke(
        album: AlbumUiModel
    ): Flow<ResultModel<AlbumDetailResponseModel>> = flow {
        try {
            emit(ResultModel.Loading())
            val response = if (album.mbid.isNullOrEmpty()) {
                repository.getAlbumDetailsWithArtisNameAndAlbumName(album.artistName, album.name)

            } else {
                repository.getAlbumDetailsWithMbid(album.mbid)
            }
            if (response.isSuccessful) {
                response.body()?.let {
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


