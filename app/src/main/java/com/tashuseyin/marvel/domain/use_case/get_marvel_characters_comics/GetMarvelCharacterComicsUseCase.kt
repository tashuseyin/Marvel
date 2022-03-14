package com.tashuseyin.marvel.domain.use_case.get_marvel_characters_comics

import com.tashuseyin.marvel.common.Resource
import com.tashuseyin.marvel.data.remote.comics.toComics
import com.tashuseyin.marvel.domain.model.Comics
import com.tashuseyin.marvel.domain.repository.MarvelCharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMarvelCharacterComicsUseCase @Inject constructor(
    private val repository: MarvelCharacterRepository
) {

    operator fun invoke(
        characterId: Int
    ): Flow<Resource<List<Comics>>> = flow {
        try {
            emit(Resource.Loading())
            val data =
                repository.getMarvelCharacterComics(
                    characterId
                ).data.results.map { it.toComics() }
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexcepted error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }


    }
}