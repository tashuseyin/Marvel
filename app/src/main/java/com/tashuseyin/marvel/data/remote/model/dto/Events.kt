package com.tashuseyin.marvel.data.remote.model.dto


import com.google.gson.annotations.SerializedName

data class Events(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ItemX>,
    @SerializedName("returned")
    val returned: Int
)