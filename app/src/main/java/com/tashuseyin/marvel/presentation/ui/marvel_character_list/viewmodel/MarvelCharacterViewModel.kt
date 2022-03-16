package com.tashuseyin.marvel.presentation.ui.marvel_character_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tashuseyin.marvel.domain.model.MarvelCharacter
import com.tashuseyin.marvel.domain.repository.MarvelCharacterRepository
import com.tashuseyin.marvel.presentation.ui.marvel_character_list.state.MarvelListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MarvelCharacterViewModel @Inject constructor(
    private val repository: MarvelCharacterRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MarvelListState())
    val state: StateFlow<MarvelListState> = _state

    init {
        viewModelScope.launch {
            getMarvelCharacters()
        }

    }

    private suspend fun getMarvelCharacters() {
        _state.value = MarvelListState(isLoading = true)
        try {
            repository.getMarvelCharacters().distinctUntilChanged().collectLatest {
                _state.value = MarvelListState(characters = it)
            }
        } catch (e: HttpException) {
            _state.value =
                MarvelListState(error = e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            _state.value =
                MarvelListState(error = "Couldn't reach server. Check your internet connection.")
        }
    }


    fun getData(): Flow<PagingData<MarvelCharacter>> {
        return repository.getMarvelCharacters().cachedIn(viewModelScope)
    }
}