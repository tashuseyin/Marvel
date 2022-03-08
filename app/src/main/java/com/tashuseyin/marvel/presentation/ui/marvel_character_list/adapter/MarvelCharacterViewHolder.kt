package com.tashuseyin.marvel.presentation.ui.marvel_character_list.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tashuseyin.marvel.databinding.CharacterRowLayoutBinding
import com.tashuseyin.marvel.domain.model.MarvelCharacter

class MarvelCharacterViewHolder(private val binding: CharacterRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(marvelCharacter: MarvelCharacter){
        val imageUrl = marvelCharacter.thumbnail.path + "/portrait_uncanny." + marvelCharacter.thumbnail.extension
        binding.characterImage.load(imageUrl){
            crossfade(300)
        }
        binding.characterName.text = marvelCharacter.name
    }
}