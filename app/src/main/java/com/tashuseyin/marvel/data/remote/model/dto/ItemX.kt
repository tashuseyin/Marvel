package com.tashuseyin.marvel.data.remote.model.dto


import com.google.gson.annotations.SerializedName

data class ItemX(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String
)