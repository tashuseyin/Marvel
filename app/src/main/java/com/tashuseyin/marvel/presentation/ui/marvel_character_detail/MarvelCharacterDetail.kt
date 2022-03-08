package com.tashuseyin.marvel.presentation.ui.marvel_character_detail

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.tashuseyin.marvel.databinding.FragmentMarvelCharacterDetailBinding
import com.tashuseyin.marvel.presentation.bindingadapter.BindingFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MarvelCharacterDetail : BindingFragment<FragmentMarvelCharacterDetailBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentMarvelCharacterDetailBinding::inflate


}