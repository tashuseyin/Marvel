package com.tashuseyin.marvel.data.remote.model.comics


import com.google.gson.annotations.SerializedName

data class Creators(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ItemXComics>,
    @SerializedName("returned")
    val returned: Int
)