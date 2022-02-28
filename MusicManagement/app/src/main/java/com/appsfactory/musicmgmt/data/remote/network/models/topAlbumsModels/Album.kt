package com.appsfactory.musicmgmt.data.remote.network.models.topAlbumsModels

import com.appsfactory.musicmgmt.data.remote.network.models.artistSearchModels.Image
import com.appsfactory.musicmgmt.presentation.uiModels.AlbumUiModel
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class Album(
    val artist: Artist?,
    val image: List<Image>,
    val mbid: String?,
    val name: String,
    @Json(name = "playcount") @SerializedName("playcount") val playCount: Int?,
    val url: String?
) {
    fun convertToAlbumUiModel(): AlbumUiModel {
        return AlbumUiModel(
            this.artist?.name,
            this.image[1].text.toString(),
            this.mbid,
            this.name,
            0
        )

    }
}