package com.appsfactory.musicmgmt.data.remote.network.models.artistSearchModels

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class Image(
    @Json(name = "#text") @SerializedName("#text") val text: String? = null,
    val size: String? = null
)