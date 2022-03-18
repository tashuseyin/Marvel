package com.tashuseyin.marvel.presentation.ui.marvel_character_list.state

import androidx.paging.PagingData
import com.tashuseyin.marvel.domain.model.MarvelCharacter

data class MarvelListState(
    val isLoading: Boolean = false,
    val characters: PagingData<MarvelCharacter>? = null,
    val error: String = ""
)
