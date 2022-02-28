package com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels

import com.appsfactory.musicmgmt.data.remote.network.SingleToArray

data class Tags(@SingleToArray val tag: List<Tag>?)

