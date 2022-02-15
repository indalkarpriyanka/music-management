package com.appsfactory.musicmgmt.data.remote.network.models.artistSearchModels

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text") val text: String? = null,
    val size: String? = null
)