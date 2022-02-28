package com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class Streamable(
    @Json(name = "#text")@SerializedName("#text") val text: String?,
    @Json(name = "fulltrack")@SerializedName("fulltrack") val fullTrack: String?
)