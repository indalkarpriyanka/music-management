package com.appsfactory.musicmgmt.domain.usecases

import com.appsfactory.musicmgmt.data.local.dao.AlbumEntity
import com.appsfactory.musicmgmt.repository.Repository


class GetAlbumDetailsFromDbUsecase(private val repository: Repository) {

    suspend fun invoke(id: Int): AlbumEntity {
        return repository.getAlbumDetailsWithId(id)
    }
}