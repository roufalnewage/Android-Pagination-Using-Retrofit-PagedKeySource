package com.smb.smbapplication.data.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

class License {

    @field:SerializedName( "key")
    var key: String? = null

    @field:SerializedName( "name")
    var name: String? = null

    @field:SerializedName( "spdx_id")
    var spdxId: String? = null

    @field:SerializedName( "url")
    var url: String? = null

    @field:SerializedName( "node_id")
    var nodeId: String? = null

}
