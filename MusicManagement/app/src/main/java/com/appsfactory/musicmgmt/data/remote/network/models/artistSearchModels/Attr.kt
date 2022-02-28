package com.appsfactory.musicmgmt.data.remote.network.models.artistSearchModels

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class Attr(
    @Json(name = "for") @SerializedName("for") val forValue: String? = null
)