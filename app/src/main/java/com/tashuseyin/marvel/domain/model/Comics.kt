package com.tashuseyin.marvel.domain.model

import com.google.gson.annotations.SerializedName
import com.tashuseyin.marvel.data.remote.model.comics.Date
import com.tashuseyin.marvel.data.remote.model.comics.Thumbnail

data class Comics(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("dates")
    val dates: List<Date>,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    var date: String?
    )
