package com.appsfactory.musicmgmt.domain.usecases

import com.appsfactory.musicmgmt.data.local.dao.AlbumEntity
import com.appsfactory.musicmgmt.data.repository.Repository

class SaveAlbumDetailsUseCase(private val repository: Repository) {

    suspend fun invoke(albumEntity: AlbumEntity) {
        repository.insertAlbum(albumEntity)
    }
}