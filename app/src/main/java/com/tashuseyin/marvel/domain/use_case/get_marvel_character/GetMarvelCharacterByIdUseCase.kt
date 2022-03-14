package com.tashuseyin.marvel.domain.use_case.get_marvel_character

import com.tashuseyin.marvel.common.Resource
import com.tashuseyin.marvel.data.remote.dto.toMarvelCharacter
import com.tashuseyin.marvel.domain.model.MarvelCharacter
import com.tashuseyin.marvel.domain.repository.MarvelCharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMarvelCharacterByIdUseCase @Inject constructor(
    private val repository: MarvelCharacterRepository
) {

    operator fun invoke(
        characterId: Int
    ): Flow<Resource<MarvelCharacter>> = flow {
        try {
            emit(Resource.Loading())
            val data =
                repository.getMarvelCharacterById(
                    characterId
                ).data.results[0].toMarvelCharacter()
            emit(Resource.Success(data))
            print(data)
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}