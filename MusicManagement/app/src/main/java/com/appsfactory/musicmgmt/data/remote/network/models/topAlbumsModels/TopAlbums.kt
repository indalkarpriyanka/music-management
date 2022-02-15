package com.appsfactory.musicmgmt.data.remote.network.models.topAlbumsModels

import com.google.gson.annotations.SerializedName

data class TopAlbums(
    @SerializedName("@attr") val attr: Attr,
    val album: List<Album>
)