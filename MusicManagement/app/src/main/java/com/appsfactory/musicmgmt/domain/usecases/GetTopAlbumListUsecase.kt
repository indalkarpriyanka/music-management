package com.appsfactory.musicmgmt.domain.usecases

import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.repository.Repository
import com.appsfactory.musicmgmt.presentation.uiModels.AlbumUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetTopAlbumListUsecase(private val repository: Repository) {

    operator fun invoke(artistName: String): Flow<ResultModel<ArrayList<AlbumUiModel>>> = flow {
        try {

            var albumList = ArrayList<AlbumUiModel>()
            emit(ResultModel.Loading())
            val topAlbumsResponseModel = repository.getTopAlbumList(artistName)
            if (topAlbumsResponseModel.isSuccessful) {
                topAlbumsResponseModel.body()?.let {

                    it.topAlbums.album.forEach { album ->

                        if (album.name != "(null)")
                            albumList.add(album.convertToAlbumUiModel())
                    }
                    emit(ResultModel.Success(albumList))
                }
            }
        } catch (e: HttpException) {
            emit(ResultModel.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(ResultModel.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}