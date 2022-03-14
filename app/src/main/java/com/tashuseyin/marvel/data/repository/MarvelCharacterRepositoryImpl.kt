package com.tashuseyin.marvel.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tashuseyin.marvel.data.paging.MarvelCharactersPagingSource
import com.tashuseyin.marvel.data.remote.network.MarvelCharacterApi
import com.tashuseyin.marvel.domain.model.MarvelCharacter
import com.tashuseyin.marvel.domain.repository.MarvelCharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarvelCharacterRepositoryImpl @Inject constructor(
    private val api: MarvelCharacterApi
) : MarvelCharacterRepository {

    override fun getMarvelCharacters(): Flow<PagingData<MarvelCharacter>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 1),
            pagingSourceFactory = {
                MarvelCharactersPagingSource(api)
            }
        ).flow
    }

    override suspend fun getMarvelCharacterById(characterId: Int) =
        api.getMarvelCharacterById(characterId)

    override suspend fun getMarvelCharacterComics(
        characterId: Int
    ) = api.getMarvelCharacterComics(characterId)
}