package com.appsfactory.musicmgmt.data.remote.network.models.artistSearchModels

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("@attr") val attr: Attr?,
    @SerializedName("artistmatches") val artistMatches: ArtistMatches?,
    @SerializedName("opensearch:Query") val openSearchQuery: OpenSearchQuery?,
    @SerializedName("opensearch:itemsPerPage") val openSearchItemsPerPage: String? = null,
    @SerializedName("opensearch:startIndex") val openSearchStartIndex: String? = null,
    @SerializedName("opensearch:totalResults") val openSearchTotalResults: String? = null
)