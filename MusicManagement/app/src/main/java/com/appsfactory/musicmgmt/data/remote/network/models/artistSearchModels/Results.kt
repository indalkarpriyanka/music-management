package com.appsfactory.musicmgmt.data.remote.network.models.artistSearchModels

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class Results(
    @Json(name = "@attr") @SerializedName("@attr") val attr: Attr?,
    @Json(name = "artistmatches") @SerializedName("artistmatches") val artistMatches: ArtistMatches?,
    @Json(name = "opensearch:Query") @SerializedName("opensearch:Query") val openSearchQuery: OpenSearchQuery?,
    @Json(name = "opensearch:itemsPerPage") @SerializedName("opensearch:itemsPerPage") val openSearchItemsPerPage: String? = null,
    @Json(name = "opensearch:startIndex") @SerializedName("opensearch:startIndex") val openSearchStartIndex: String? = null,
    @Json(name = "opensearch:totalResults") @SerializedName("opensearch:totalResults") val openSearchTotalResults: String? = null
)