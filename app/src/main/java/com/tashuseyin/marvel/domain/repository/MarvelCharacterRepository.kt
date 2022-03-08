package com.tashuseyin.marvel.domain.repository

import com.tashuseyin.marvel.data.remote.dto.Result

interface MarvelCharacterRepository {
    suspend fun getMarvelCharacters(queries: Map<String, String>): Result
}