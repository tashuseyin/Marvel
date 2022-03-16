package com.tashuseyin.marvel.data.remote.model.comics


import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ItemComics>,
    @SerializedName("returned")
    val returned: Int
)