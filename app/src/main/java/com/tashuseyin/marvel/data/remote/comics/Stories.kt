package com.tashuseyin.marvel.data.remote.comics


import com.google.gson.annotations.SerializedName

data class Stories(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ItemXXComics>,
    @SerializedName("returned")
    val returned: Int
)