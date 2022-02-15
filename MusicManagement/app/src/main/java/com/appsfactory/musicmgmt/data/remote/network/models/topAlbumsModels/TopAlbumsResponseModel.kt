package com.appsfactory.musicmgmt.data.remote.network.models.topAlbumsModels

import com.google.gson.annotations.SerializedName

data class TopAlbumsResponseModel(
    @SerializedName("topalbums")val topAlbums: TopAlbums
)