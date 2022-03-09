package com.tashuseyin.marvel.data.remote.comics


import com.google.gson.annotations.SerializedName

data class ItemXComics(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("role")
    val role: String
)