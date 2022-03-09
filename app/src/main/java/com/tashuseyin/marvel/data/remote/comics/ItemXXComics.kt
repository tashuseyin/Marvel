package com.tashuseyin.marvel.data.remote.comics


import com.google.gson.annotations.SerializedName

data class ItemXXComics(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("type")
    val type: String
)