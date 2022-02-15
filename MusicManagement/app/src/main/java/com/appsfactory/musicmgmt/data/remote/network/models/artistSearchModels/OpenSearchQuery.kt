package com.appsfactory.musicmgmt.data.remote.network.models.artistSearchModels

import com.google.gson.annotations.SerializedName

data class OpenSearchQuery(
    @SerializedName("#text") val text: String? = null,
    val role: String? = null,
    val searchTerms: String? = null,
    val startPage: String? = null
)