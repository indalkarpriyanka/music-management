package com.appsfactory.musicmgmt.domain.usecases

import com.appsfactory.musicmgmt.domain.repository.Repository

class RemoveAlbumFromDbUsecase(private val repository: Repository) {

    suspend fun invoke(id:Int){
        repository.removeAlbum(id)
    }
}