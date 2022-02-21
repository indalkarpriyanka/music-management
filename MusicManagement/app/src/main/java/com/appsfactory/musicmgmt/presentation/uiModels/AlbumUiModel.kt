package com.appsfactory.musicmgmt.presentation.uiModels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumUiModel(
    val artistName: String?,
    val image: String,
    val mbid: String?,
    val name: String
) : Parcelable