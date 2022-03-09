package com.tashuseyin.marvel.domain.repository

import com.tashuseyin.marvel.data.remote.comics.CharacterComics
import com.tashuseyin.marvel.data.remote.dto.Result

interface MarvelCharacterRepository {
    suspend fun getMarvelCharacters(queries: Map<String, String>): Result
    suspend fun getMarvelCharacterById(characterId: Int,queries: Map<String, String>): Result
    suspend fun getMarvelCharacterComics(characterId: Int, queries: Map<String, String>): CharacterComics
}