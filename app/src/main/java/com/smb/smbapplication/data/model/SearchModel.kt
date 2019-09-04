package com.smb.smbapplication.data.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class SearchModel (

    @field:SerializedName( "total_count")
    var totalCount: Int? = null,

    @field:SerializedName( "incomplete_results")
    var incompleteResults: Boolean? = null,

    @field:SerializedName( "items")
    var searchItems: List<SearchItem>? = null

    )
