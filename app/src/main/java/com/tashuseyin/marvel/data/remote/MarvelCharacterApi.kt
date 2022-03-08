package com.tashuseyin.marvel.data.remote

import com.tashuseyin.marvel.data.remote.dto.Result
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelCharacterApi {
    @GET("/v1/public/characters")
    suspend fun getMarvelCharacters(@QueryMap queries: Map<String, String>): Result
}