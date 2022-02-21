package com.appsfactory.musicmgmt.common

import com.appsfactory.musicmgmt.data.local.dao.AlbumEntity
import kotlinx.coroutines.flow.Flow

sealed class ResultModel<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ResultModel<T>(data)
    class Error<T>(message: String, data: T? = null) : ResultModel<T>(data, message)
    class Loading<T> : ResultModel<T>()
}