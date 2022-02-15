package com.appsfactory.musicmgmt.data.remote.network


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Api {
    private const val BASE_URL = "https://ws.audioscrobbler.com/2.0/"

    private val httpClient: OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    var retrofit: Retrofit =
        Retrofit.Builder().client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build()

}

