package com.appsfactory.musicmgmt.domain.usecases

import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.data.repository.Repository
import com.appsfactory.musicmgmt.presentation.uiModels.AlbumUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMyAlbumListUsecase(private val repository: Repository) {

    operator fun invoke(): Flow<ResultModel<ArrayList<AlbumUiModel>>> = flow {

        repository.getMyAlbumList().collect {
            var albumList = ArrayList<AlbumUiModel>()
            it.forEach { album ->
                // if (album.name != "(null)")
                albumList.add(album.convertToAlbumUiModel())
            }
            emit(ResultModel.Success(albumList))
        }
    }
}