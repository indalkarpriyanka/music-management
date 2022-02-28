package com.appsfactory.musicmgmt.data.local

import androidx.room.TypeConverter
import com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels.Track
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class DataConverter {
    @TypeConverter
    fun fromTrackList(tracks: List<Track>?): String? {
        tracks?.let {
            val gson = Gson()
            val type: Type = object : TypeToken<List<Track>>() {}.type
            return gson.toJson(tracks, type)
        }
        return null
    }

    @TypeConverter
    fun toTrackList(trackString: String?): List<Track>? {
        trackString?.let {
            val gson = Gson()
            val type: Type = object : TypeToken<List<Track>>() {}.type
            return gson.fromJson<List<Track>>(trackString, type)
        }
        return null
    }
}