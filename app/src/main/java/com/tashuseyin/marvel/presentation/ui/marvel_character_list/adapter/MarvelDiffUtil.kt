package com.tashuseyin.marvel.presentation.ui.marvel_character_list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.tashuseyin.marvel.domain.model.MarvelCharacter

class MarvelDiffUtil : DiffUtil.ItemCallback<MarvelCharacter>() {
    override fun areItemsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter) =
        oldItem == newItem
}