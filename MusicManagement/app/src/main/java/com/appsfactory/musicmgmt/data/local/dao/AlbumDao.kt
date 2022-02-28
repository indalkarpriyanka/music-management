package com.appsfactory.musicmgmt.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {

    @Insert
    suspend fun insertAlbum(album: AlbumEntity)

    @Query("SELECT * FROM album_table ORDER BY id")
    fun getAllAlbums(): Flow<List<AlbumEntity>>

    @Query("SELECT * FROM album_table where id= :id")
    suspend fun getAlbumWithId(id: Int): AlbumEntity

    @Query("DELETE FROM album_table WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT id FROM album_table WHERE name=:albumName AND artist_name=:artistName")
    suspend fun getAlbumId(albumName: String, artistName: String): Int
}