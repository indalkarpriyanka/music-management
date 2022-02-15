package com.appsfactory.musicmgmt.presentation.uiModels

import com.google.gson.annotations.SerializedName

data class ArtistUiModel(
    @SerializedName("mbid") var mBid: String? = null,
    var name: String? = null,
)