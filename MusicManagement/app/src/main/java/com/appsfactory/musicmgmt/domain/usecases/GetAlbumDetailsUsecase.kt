package com.appsfactory.musicmgmt.domain.usecases

import android.util.Log
import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.data.local.dao.AlbumEntity
import com.appsfactory.musicmgmt.repository.Repository
import com.appsfactory.musicmgmt.presentation.uiModels.AlbumUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.lang.Exception

class GetAlbumDetailsUsecase(private val repository: Repository) {

    suspend operator fun invoke(
        album: AlbumUiModel
    ): Flow<ResultModel<AlbumEntity>> = flow {

        lateinit var albumEntity: AlbumEntity
        try {
            emit(ResultModel.Loading())
            val response = if (album.mbid.isNullOrEmpty()) {
                repository.getAlbumDetailsWithArtisNameAndAlbumName(album.artistName, album.name)
            } else {
                repository.getAlbumDetailsWithMbid(album.mbid)
            }
            if (response.isSuccessful) {
                response.body()?.let {

                    albumEntity = it.album.toAlbumEntity()
                    if (repository.getAlbumIdFromDbIfPresent(albumEntity) != null)
                        albumEntity.id = repository.getAlbumIdFromDbIfPresent(albumEntity)
                    emit(ResultModel.Success(albumEntity))
                }
            }
        } catch (e: Exception) {
            Log.e("Error",e.localizedMessage)
            emit(ResultModel.Error("An unexpected error occured"))
        }
    }
}


