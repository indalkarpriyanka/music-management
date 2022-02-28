package com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels

import com.appsfactory.musicmgmt.data.remote.network.models.topAlbumsModels.Artist
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class Track(
    @Json(name = "@attr")@SerializedName("@attr") val attr: Attr?,
    val artist: Artist?,
    val duration: Int?=0,
    val name: String?,
    val streamable: Streamable?,
    val url: String?
)