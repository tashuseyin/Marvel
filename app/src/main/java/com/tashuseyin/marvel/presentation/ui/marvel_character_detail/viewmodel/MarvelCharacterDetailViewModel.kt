package com.tashuseyin.marvel.presentation.ui.marvel_character_detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tashuseyin.marvel.common.Constants
import com.tashuseyin.marvel.data.remote.model.comics.toComics
import com.tashuseyin.marvel.data.remote.model.dto.toMarvelCharacter
import com.tashuseyin.marvel.domain.repository.MarvelCharacterRepository
import com.tashuseyin.marvel.presentation.ui.marvel_character_detail.state.MarvelDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MarvelCharacterDetailViewModel @Inject constructor(
    private val repository: MarvelCharacterRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _marvelCharacter: MutableLiveData<MarvelDetailState> = MutableLiveData()
    val marvelCharacter get() = _marvelCharacter

    init {
        savedStateHandle.get<Int>(Constants.QUERY_CHARACTER_ID)?.let { characterId ->
            viewModelScope.launch {
                getMarvelCharacterByIdAndComics(characterId)
            }
        }
    }

    suspend fun retry(characterId: Int) {
        getMarvelCharacterByIdAndComics(characterId)
    }

    private suspend fun getMarvelCharacterByIdAndComics(characterId: Int) {
        _marvelCharacter.value = MarvelDetailState(isLoading = true)
        try {
            val responseCharacter =
                repository.getMarvelCharacterById(characterId).data.results[0].toMarvelCharacter()
            val responseComics =
                repository.getMarvelCharacterComics(characterId).data.results.map { it.toComics() }
            _marvelCharacter.value =
                MarvelDetailState(character = responseCharacter, comics = responseComics)
        } catch (e: HttpException) {
            _marvelCharacter.value =
                MarvelDetailState(
                    error = e.localizedMessage ?: "An unexpected error occurred",
                    errorShowing = true
                )
        } catch (e: IOException) {
            _marvelCharacter.value =
                MarvelDetailState(
                    error = "Couldn't reach server. Check your internet connection.",
                    errorShowing = true
                )
        }
    }
}