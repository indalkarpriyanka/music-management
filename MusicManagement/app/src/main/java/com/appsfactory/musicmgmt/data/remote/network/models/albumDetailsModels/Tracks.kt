package com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels


import com.appsfactory.musicmgmt.data.remote.network.SingleToArray
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class Tracks(@SingleToArray val track: List<Track>?)