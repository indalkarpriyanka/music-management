package com.appsfactory.musicmgmt.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.appsfactory.musicmgmt.view.fragments.DialogFragment

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
    const val DOWNLOAD = "Download"
    const val REMOVE = "Remove"
    const val API_KEY_VALUE = "4ecb6f7e03ba6c217802a3185a7f4038"
    const val MESSAGE = "MESSAGE"
    private const val INTERNET_MESSAGE = "Please check your internet connection."

    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return result
    }

    fun showInternetErrorDialog(supportFragmentManager: FragmentManager) {
        var dialogFragment = DialogFragment()
        val args = Bundle()
        args.putString(MESSAGE, INTERNET_MESSAGE)
        dialogFragment.arguments = args
        dialogFragment.show(supportFragmentManager, "Error")
    }
}