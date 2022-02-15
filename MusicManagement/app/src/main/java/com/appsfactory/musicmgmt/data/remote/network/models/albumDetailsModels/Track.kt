package com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels

import com.appsfactory.musicmgmt.data.remote.network.models.topAlbumsModels.Artist
import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("@attr") val attr: Attr,
    val artist: Artist,
    val duration: Int,
    val name: String,
    val streamable: Streamable,
    val url: String
)