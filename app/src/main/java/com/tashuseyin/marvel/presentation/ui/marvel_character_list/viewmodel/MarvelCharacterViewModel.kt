package com.tashuseyin.marvel.presentation.ui.marvel_character_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tashuseyin.marvel.domain.model.MarvelCharacter
import com.tashuseyin.marvel.domain.repository.MarvelCharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelCharacterViewModel @Inject constructor(
    private val repository: MarvelCharacterRepository
) : ViewModel() {

    private val _marvelCharacterList = MutableStateFlow(PagingData.empty<MarvelCharacter>())
    val marvelCharacterList: StateFlow<PagingData<MarvelCharacter>> = _marvelCharacterList

    init {
        viewModelScope.launch {
            getMarvelCharacters()
        }
    }

    private suspend fun getMarvelCharacters() {
        repository.getMarvelCharacters().cachedIn(viewModelScope).distinctUntilChanged().collectLatest {
            _marvelCharacterList.value = it
        }
    }
}