package com.tashuseyin.marvel.domain.model

import com.google.gson.annotations.SerializedName
import com.tashuseyin.marvel.data.remote.model.dto.Comics
import com.tashuseyin.marvel.data.remote.model.dto.Thumbnail


data class MarvelCharacter(
    @SerializedName("id")
    val id: Int,
    @SerializedName("comics")
    val comics: Comics,
    @SerializedName("description")
    val description: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail
)
