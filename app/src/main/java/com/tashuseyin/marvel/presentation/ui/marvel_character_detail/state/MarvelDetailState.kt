package com.tashuseyin.marvel.presentation.ui.marvel_character_detail.state

import com.tashuseyin.marvel.domain.model.Comics
import com.tashuseyin.marvel.domain.model.MarvelCharacter

data class MarvelDetailState(
    val isLoading: Boolean = false,
    val character: MarvelCharacter? = null,
    val comics: List<Comics> = emptyList(),
    val errorShowing: Boolean = false,
    val error: String = ""
)
