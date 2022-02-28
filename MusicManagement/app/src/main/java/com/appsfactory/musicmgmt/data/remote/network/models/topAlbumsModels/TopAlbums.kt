package com.appsfactory.musicmgmt.data.remote.network.models.topAlbumsModels

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class TopAlbums(
    @Json(name = "@attr")@SerializedName("@attr") val attr: Attr,
    val album: List<Album>
)