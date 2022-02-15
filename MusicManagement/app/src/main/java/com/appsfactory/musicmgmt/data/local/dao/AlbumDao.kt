package com.appsfactory.musicmgmt.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {

    @Insert
    suspend fun insertAlbum(album: AlbumEntity)

    @Query("SELECT * FROM album_table ORDER BY mbid")
    fun getAllAlbums(): Flow<List<AlbumEntity>>

    @Delete
    fun delete(album: AlbumEntity)
}