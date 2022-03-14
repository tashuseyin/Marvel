package com.tashuseyin.marvel.domain.use_case.get_marvel_characters

import androidx.paging.PagingData
import com.tashuseyin.marvel.common.Resource
import com.tashuseyin.marvel.domain.model.MarvelCharacter
import com.tashuseyin.marvel.domain.repository.MarvelCharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMarvelCharactersUseCase @Inject constructor(
    private val repository: MarvelCharacterRepository
) {

    operator fun invoke(): Flow<Resource<PagingData<MarvelCharacter>>> = flow {
        try {
            emit(Resource.Loading())
            repository.getMarvelCharacters().collect {
                emit(Resource.Success(data = it))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}