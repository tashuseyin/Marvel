package com.tashuseyin.marvel.presentation.ui.marvel_character_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tashuseyin.marvel.common.Constants.LIMIT
import com.tashuseyin.marvel.common.Constants.MD5_API_KEY
import com.tashuseyin.marvel.common.Constants.PUBLIC_API_KEY
import com.tashuseyin.marvel.common.Constants.QUERY_API_KEY
import com.tashuseyin.marvel.common.Constants.QUERY_HASH
import com.tashuseyin.marvel.common.Constants.QUERY_LIMIT
import com.tashuseyin.marvel.common.Constants.QUERY_TS
import com.tashuseyin.marvel.common.Constants.TS
import com.tashuseyin.marvel.common.Resource
import com.tashuseyin.marvel.domain.use_case.get_marvel_characters.GetMarvelCharactersUseCase
import com.tashuseyin.marvel.presentation.ui.marvel_character_list.state.MarvelListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MarvelCharacterViewModel @Inject constructor(
    private val getMarvelCharactersUseCase: GetMarvelCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MarvelListState())
    val state: StateFlow<MarvelListState> = _state

    init {
        getMarvelCharacters(applyQueries())
    }

    private fun getMarvelCharacters(queries: HashMap<String, String>) {
        getMarvelCharactersUseCase(queries).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MarvelListState(characters = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =
                        MarvelListState(error = result.message ?: "An unexcepted error occurred")
                }
                is Resource.Loading -> {
                    _state.value = MarvelListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_TS] = TS
        queries[QUERY_API_KEY] = PUBLIC_API_KEY
        queries[QUERY_HASH] = MD5_API_KEY
        queries[QUERY_LIMIT] = LIMIT
        return queries
    }
}