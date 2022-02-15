package com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels

import com.google.gson.annotations.SerializedName

data class Streamable(
    @SerializedName("#text") val text: String,
    val fulltrack: String
)