package com.appsfactory.musicmgmt.data.remote.network

import com.appsfactory.musicmgmt.common.utils.Constants
import com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels.AlbumDetailResponseModel
import com.appsfactory.musicmgmt.data.remote.network.models.artistSearchModels.SearchArtistResponse
import com.appsfactory.musicmgmt.data.remote.network.models.topAlbumsModels.TopAlbumsResponseModel
import retrofit2.Response
import retrofit2.http.*

interface MusicMgmtService {
    @GET(".")
    suspend fun searchArtist(
        @Query(Constants.METHOD) method: String,
        @Query(Constants.ARTIST) searchText: String,
        @Query(Constants.API_KEY) apiKey: String,
        @Query(Constants.FORMAT) format: String
    ): Response<SearchArtistResponse>

    @GET(".")
    suspend fun getTopAlbums(
        @Query(Constants.METHOD) method: String,
        @Query(Constants.ARTIST) artistName: String,
        @Query(Constants.API_KEY) apiKey: String,
        @Query(Constants.FORMAT) format: String
    ): Response<TopAlbumsResponseModel>

    @GET(".")
    suspend fun getAlbumDetailsWithMbid(
        @Query(Constants.METHOD) method: String,
        @Query(Constants.API_KEY) apiKey: String,
        @Query(Constants.MBID) mbid: String,
        @Query(Constants.FORMAT) format: String
    ): Response<AlbumDetailResponseModel>

    @GET(".")
    suspend fun getAlbumDetailsWithAlbumAndArtistName(
        @Query(Constants.METHOD) method: String,
        @Query(Constants.API_KEY) apiKey: String,
        @Query(Constants.ARTIST) artistName: String?,
        @Query(Constants.ALBUM) albumName: String,
        @Query(Constants.FORMAT) format: String
    ): Response<AlbumDetailResponseModel>
}