package com.appsfactory.musicmgmt.repository

import com.appsfactory.musicmgmt.data.remote.network.MusicMgmtService
import com.appsfactory.musicmgmt.common.utils.Constants
import com.appsfactory.musicmgmt.data.local.dao.AlbumDao
import com.appsfactory.musicmgmt.data.local.dao.AlbumEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


class Repository(private val retrofitService: MusicMgmtService, private val albumDao: AlbumDao) {

    suspend fun getArtistList(searchText: String) = withContext(Dispatchers.IO) {
        retrofitService.searchArtist(
            Constants.METHOD_SEARCH,
            searchText,
            Constants.API_KEY_VALUE,
            Constants.FORMAT_VALUE
        )
    }

    suspend fun getTopAlbumList(artistName: String) = withContext(Dispatchers.IO) {
        retrofitService.getTopAlbums(
            Constants.METHOD_GET_TOP_ALBUMS,
            artistName,
            Constants.API_KEY_VALUE,
            Constants.FORMAT_VALUE
        )
    }

    suspend fun getAlbumDetailsWithMbid(mbid: String) = withContext(Dispatchers.IO) {
        retrofitService.getAlbumDetailsWithMbid(
            Constants.METHOD_GET_ALBUMS_INFO,
            Constants.API_KEY_VALUE,
            mbid,
            Constants.FORMAT_VALUE
        )
    }

    suspend fun getAlbumDetailsWithArtisNameAndAlbumName(artistName: String?, albumName: String) =
        withContext(Dispatchers.IO) {
            retrofitService.getAlbumDetailsWithAlbumAndArtistName(
                Constants.METHOD_GET_ALBUMS_INFO,
                Constants.API_KEY_VALUE,
                artistName,
                albumName,
                Constants.FORMAT_VALUE
            )
        }

    fun getMyAlbumList(): Flow<List<AlbumEntity>> {
        return albumDao.getAllAlbums()
    }

    suspend fun getAlbumDetailsWithId(id: Int): AlbumEntity {
        return albumDao.getAlbumWithId(id)
    }

    suspend fun insertAlbum(albumEntity: AlbumEntity) {
        albumDao.insertAlbum(albumEntity)
    }

    suspend fun getAlbumIdFromDbIfPresent(albumEntity: AlbumEntity): Int {
        return albumDao.getAlbumId(albumEntity.name, albumEntity.artistName.toString())
    }

    suspend fun removeAlbum(id: Int) {
        albumDao.delete(id)
    }
}