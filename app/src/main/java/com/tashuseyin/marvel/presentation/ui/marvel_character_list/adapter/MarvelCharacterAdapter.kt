package com.tashuseyin.marvel.presentation.ui.marvel_character_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tashuseyin.marvel.databinding.CharacterRowLayoutBinding
import com.tashuseyin.marvel.domain.model.MarvelCharacter

class MarvelCharacterAdapter : RecyclerView.Adapter<MarvelCharacterViewHolder>() {
    var marvelCharacterList = emptyList<MarvelCharacter>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterViewHolder {
        val binding =
            CharacterRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarvelCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        holder.bind(marvelCharacterList[position])
    }

    override fun getItemCount() = marvelCharacterList.size

    fun setData(newCharacterList: List<MarvelCharacter>) {
        this.marvelCharacterList = newCharacterList
    }
}