package com.appsfactory.musicmgmt.data.remote.network.models.topAlbumsModels

import com.appsfactory.musicmgmt.data.remote.network.models.artistSearchModels.Image
import com.google.gson.annotations.SerializedName

data class Album(
    val artist: Artist,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    @SerializedName("playcount") val playCount: Int,
    val url: String
)