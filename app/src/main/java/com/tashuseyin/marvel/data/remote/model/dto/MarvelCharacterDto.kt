package com.tashuseyin.marvel.data.remote.model.dto


import com.google.gson.annotations.SerializedName
import com.tashuseyin.marvel.domain.model.MarvelCharacter

data class MarvelCharacterDto(
    @SerializedName("comics")
    val comics: Comics,
    @SerializedName("description")
    val description: String?,
    @SerializedName("events")
    val events: Events,
    @SerializedName("id")
    val id: Int,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("series")
    val series: Series,
    @SerializedName("stories")
    val stories: Stories,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("urls")
    val urls: List<Url>
)

fun MarvelCharacterDto.toMarvelCharacter(): MarvelCharacter {
    return MarvelCharacter(
        id = id,
        comics = comics,
        description = description,
        name = name,
        thumbnail = thumbnail
    )
}