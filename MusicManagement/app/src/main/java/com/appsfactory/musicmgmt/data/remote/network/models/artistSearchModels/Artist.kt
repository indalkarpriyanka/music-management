package com.appsfactory.musicmgmt.data.remote.network.models.artistSearchModels

import com.google.gson.annotations.SerializedName

data class Artist(
    val image: List<Image> = emptyList(),
    var listeners: String? = null,
    @SerializedName("mbid") var mBid: String? = null,
    var name: String? = null,
    var streamable: String? = null,
    var url: String? = null
)