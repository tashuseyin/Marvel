package com.tashuseyin.marvel.presentation.ui.marvel_character_detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tashuseyin.marvel.common.Constants
import com.tashuseyin.marvel.common.Resource
import com.tashuseyin.marvel.domain.use_case.get_marvel_character.GetMarvelCharacterByIdUseCase
import com.tashuseyin.marvel.domain.use_case.get_marvel_characters_comics.GetMarvelCharacterComicsUseCase
import com.tashuseyin.marvel.presentation.ui.marvel_character_detail.state.MarvelDetailState
import com.tashuseyin.marvel.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MarvelCharacterDetailViewModel @Inject constructor(
    private val getMarvelCharacterByIdUseCase: GetMarvelCharacterByIdUseCase,
    private val getMarvelCharacterComicsUseCase: GetMarvelCharacterComicsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(MarvelDetailState())
    val state: StateFlow<MarvelDetailState> = _state

    init {
        savedStateHandle.get<Int>(Constants.QUERY_CHARACTER_ID)?.let { characterId ->
            getMarvelCharacterById(characterId, Utils.applyQueries())
            getMarvelCharacterComics(characterId, Utils.applyQueries())
        }
    }

    private fun getMarvelCharacterById(characterId: Int, queries: Map<String, String>) {
        getMarvelCharacterByIdUseCase(characterId, queries).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MarvelDetailState(character = result.data)
                }
                is Resource.Error -> {
                    _state.value =
                        MarvelDetailState(error = result.message ?: "An unexcepted error occurred")
                }
                is Resource.Loading -> {
                    _state.value = MarvelDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getMarvelCharacterComics(characterId: Int, queries: Map<String, String>) {
        getMarvelCharacterComicsUseCase(characterId, queries).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MarvelDetailState(comics = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =
                        MarvelDetailState(error = result.message ?: "An unexcepted error occurred")
                }
                is Resource.Loading -> {
                    _state.value = MarvelDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}