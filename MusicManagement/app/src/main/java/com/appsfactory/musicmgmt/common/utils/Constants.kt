package com.appsfactory.musicmgmt.common.utils

object Constants {

    const val ALBUM_MID = "album_mid"
    const val METHOD_SEARCH = "artist.search"
    const val ARTIST_NAME = "artist_name"
    const val ARTIST = "artist"
    const val ALBUM = "album"
    const val METHOD = "method"
    const val METHOD_GET_TOP_ALBUMS = "artist.gettopalbums"
    const val METHOD_GET_ALBUMS_INFO = "album.getinfo"
    const val FORMAT_VALUE = "json"
    const val FORMAT = "format"
    const val MBID = "mbid"
    const val API_KEY = "api_key"
    const val API_KEY_VALUE = "4ecb6f7e03ba6c217802a3185a7f4038"

    fun formatUrl(url: String): String {
        url.replace("\"", "", false)
        return url
    }
}