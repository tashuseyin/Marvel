package com.tashuseyin.marvel.data.remote.network

import com.tashuseyin.marvel.data.remote.comics.CharacterComics
import com.tashuseyin.marvel.data.remote.dto.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MarvelCharacterApi {
    @GET("/v1/public/characters")
    suspend fun getMarvelCharacters(@QueryMap queries: Map<String, String>): Result

    @GET("/v1/public/characters/{characterId}")
    suspend fun getMarvelCharacterById(
        @Path("characterId") characterId: Int,
        @QueryMap queries: Map<String, String>
    ): Result

    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getMarvelCharacterComics(
        @Path("characterId") characterId: Int,
        @QueryMap queries: Map<String, String>
    ): CharacterComics
}