package com.appsfactory.musicmgmt.data.remote.network.models.topAlbumsModels

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class TopAlbumsResponseModel(
    @Json(name = "topalbums")@SerializedName("topalbums")val topAlbums: TopAlbums
)