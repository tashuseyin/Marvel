package com.tashuseyin.marvel.presentation.ui.marvel_character_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tashuseyin.marvel.domain.model.MarvelCharacter
import com.tashuseyin.marvel.domain.repository.MarvelCharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MarvelCharacterViewModel @Inject constructor(
    private val repository: MarvelCharacterRepository
) : ViewModel() {

//    private val _state = MutableStateFlow(MarvelListState())
//    val state: StateFlow<MarvelListState> = _state
//
//    init {
//        getMarvelCharacters()
//    }
//
//    private fun getMarvelCharacters() {
//        getMarvelCharactersUseCase().onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _state.value = MarvelListState(characters = result.data ?: PagingData.empty())
//                }
//                is Resource.Error -> {
//                    _state.value =
//                        MarvelListState(error = result.message ?: "An unexcepted error occurred")
//                }
//                is Resource.Loading -> {
//                    _state.value = MarvelListState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }

    fun getData(): Flow<PagingData<MarvelCharacter>> {
        return repository.getMarvelCharacters().cachedIn(viewModelScope)
    }
}