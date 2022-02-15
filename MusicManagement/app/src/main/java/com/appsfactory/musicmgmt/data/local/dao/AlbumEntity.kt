package com.appsfactory.musicmgmt.data.local.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels.Track
import com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels.Tracks

@Entity(tableName = "album_table")
data class AlbumEntity(
    @ColumnInfo(name = "artist_name") val artistName: String,
    @ColumnInfo val image: String,
    @ColumnInfo val mbid: String?,
    @ColumnInfo val name: String,
    @ColumnInfo(name = "play_count") val playCount: String,
    @ColumnInfo val url: String,
    @ColumnInfo val tracks: List<Track> = emptyList(),
    @PrimaryKey(autoGenerate = true) @ColumnInfo val id: Int = 0
)


