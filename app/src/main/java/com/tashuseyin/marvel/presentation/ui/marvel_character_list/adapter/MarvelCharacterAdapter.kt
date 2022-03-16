package com.tashuseyin.marvel.presentation.ui.marvel_character_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.tashuseyin.marvel.databinding.CharacterRowLayoutBinding
import com.tashuseyin.marvel.domain.model.MarvelCharacter

class MarvelCharacterAdapter :
    PagingDataAdapter<MarvelCharacter, MarvelCharacterViewHolder>(MarvelDiffUtil()) {
    var onItemClickListener: ((Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterViewHolder {
        val binding =
            CharacterRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarvelCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        holder.bind(getItem(position)!!, onItemClickListener)
    }
}