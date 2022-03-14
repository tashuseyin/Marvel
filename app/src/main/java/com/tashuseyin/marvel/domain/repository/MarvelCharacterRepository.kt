package com.tashuseyin.marvel.domain.repository

import androidx.paging.PagingData
import com.tashuseyin.marvel.data.remote.comics.CharacterComics
import com.tashuseyin.marvel.data.remote.dto.Result
import com.tashuseyin.marvel.domain.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface MarvelCharacterRepository {
    fun getMarvelCharacters(): Flow<PagingData<MarvelCharacter>>
    suspend fun getMarvelCharacterById(characterId: Int): Result
    suspend fun getMarvelCharacterComics(characterId: Int): CharacterComics
}