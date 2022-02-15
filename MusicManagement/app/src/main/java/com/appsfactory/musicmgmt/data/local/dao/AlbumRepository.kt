package com.appsfactory.musicmgmt.data.local.dao

import com.appsfactory.musicmgmt.data.local.dao.AlbumDao
import kotlinx.coroutines.flow.Flow

class AlbumRepository(private val albumDao: AlbumDao) {
    suspend fun insertAlbum(album: AlbumEntity) {
        albumDao.insertAlbum(album)
    }

    suspend fun deletePizza(album: AlbumEntity) {
        albumDao.delete(album)
    }

    val allPizzaList: Flow<List<AlbumEntity>> = albumDao.getAllAlbums()
}