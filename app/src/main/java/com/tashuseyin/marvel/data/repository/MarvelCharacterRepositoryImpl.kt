package com.tashuseyin.marvel.data.repository

import com.tashuseyin.marvel.data.remote.MarvelCharacterApi
import com.tashuseyin.marvel.domain.repository.MarvelCharacterRepository
import javax.inject.Inject

class MarvelCharacterRepositoryImpl @Inject constructor(
    private val api: MarvelCharacterApi
) : MarvelCharacterRepository {
    override suspend fun getMarvelCharacters(queries: Map<String, String>) =
        api.getMarvelCharacters(queries)

}