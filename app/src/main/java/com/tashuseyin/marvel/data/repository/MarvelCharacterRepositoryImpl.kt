package com.tashuseyin.marvel.data.repository

import com.tashuseyin.marvel.data.remote.network.MarvelCharacterApi
import com.tashuseyin.marvel.domain.repository.MarvelCharacterRepository
import javax.inject.Inject

class MarvelCharacterRepositoryImpl @Inject constructor(
    private val api: MarvelCharacterApi
) : MarvelCharacterRepository {
    override suspend fun getMarvelCharacters(queries: Map<String, String>) =
        api.getMarvelCharacters(queries)

    override suspend fun getMarvelCharacterById(characterId: Int, queries: Map<String, String>) =
        api.getMarvelCharacterById(characterId, queries)

    override suspend fun getMarvelCharacterComics(
        characterId: Int,
        queries: Map<String, String>
    ) = api.getMarvelCharacterComics(characterId, queries)
}