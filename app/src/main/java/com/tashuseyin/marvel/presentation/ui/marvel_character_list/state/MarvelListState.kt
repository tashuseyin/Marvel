package com.tashuseyin.marvel.presentation.ui.marvel_character_list.state

import com.tashuseyin.marvel.domain.model.MarvelCharacter


data class MarvelListState(
    val isLoading: Boolean = false,
    val characters: List<MarvelCharacter> = emptyList(),
    val error: String = ""
)
