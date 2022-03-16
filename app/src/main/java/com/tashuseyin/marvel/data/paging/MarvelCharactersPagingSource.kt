package com.tashuseyin.marvel.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tashuseyin.marvel.data.remote.model.dto.toMarvelCharacter
import com.tashuseyin.marvel.data.remote.network.MarvelCharacterApi
import com.tashuseyin.marvel.domain.model.MarvelCharacter

class MarvelCharactersPagingSource(
    private val api: MarvelCharacterApi
) : PagingSource<Int, MarvelCharacter>() {

    override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
        val nextPage = params.key ?: 1
        return try {
            val response = api.getMarvelCharacters(nextPage)

            val roverInfoDetail = response.data.results.map { it.toMarvelCharacter() }

            LoadResult.Page(
                data = roverInfoDetail,
                prevKey = if (nextPage == 1) null else nextPage - 30,
                nextKey = if (roverInfoDetail.isEmpty()) null else nextPage + 30
            )
        } catch (ex: Exception) {
            return LoadResult.Error(ex)
        }
    }
}