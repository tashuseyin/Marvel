package com.tashuseyin.marvel.presentation.ui.marvel_character_detail.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tashuseyin.marvel.databinding.ComicsCardBinding
import com.tashuseyin.marvel.domain.model.Comics

class ComicsViewHolder(private val binding: ComicsCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(comics: Comics) {
        val imageUrl =
            comics.thumbnail.path + "." + comics.thumbnail.extension
        binding.comicsImage.load(imageUrl) {
            crossfade(300)
        }
        binding.comicsTitle.text = comics.title
        if (comics.description?.isNotEmpty() == true) {
            binding.comicsDescription.text = comics.description
        }
        val dateList = comics.title.split(" ").toList()
        for (i in dateList) {
            val date = i.replace("(", "").replace(")", "")
            if (date.toIntOrNull() != null) {
                binding.comicsDate.text = date
            }
        }
    }
}
