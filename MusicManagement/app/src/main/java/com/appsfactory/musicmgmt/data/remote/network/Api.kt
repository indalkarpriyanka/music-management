package com.appsfactory.musicmgmt.data.remote.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object Api {

    private const val BASE_URL = "https://ws.audioscrobbler.com/2.0/"

    private val httpClient: OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    private val moshi = Moshi.Builder()
        .add(SingleToArrayAdapter.INSTANCE)
        .add(KotlinJsonAdapterFactory())
        .build()

    var retrofit: Retrofit =
        Retrofit.Builder().client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL).build()

}

