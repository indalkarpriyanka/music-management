package com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels

import com.appsfactory.musicmgmt.data.local.dao.AlbumEntity
import com.appsfactory.musicmgmt.data.remote.network.models.artistSearchModels.Image

data class Album(
    val artist: String,
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val playcount: String,
    val tags: Tags?,
    val tracks: Tracks,
    val url: String,
    val wiki: Wiki
) {
    fun toAlbumEntity(): AlbumEntity {
        return AlbumEntity(
            artistName = artist,
            image = image[1].text.toString(),
            mbid = tracks.track[0].artist.mbid,
            name = name,
            playCount = playcount,
            url = url,
            tracks = tracks.track
        )
    }
}