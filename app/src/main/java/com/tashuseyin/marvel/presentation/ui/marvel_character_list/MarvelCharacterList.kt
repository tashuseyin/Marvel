package com.tashuseyin.marvel.presentation.ui.marvel_character_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.tashuseyin.marvel.databinding.FragmentMarvelCharacterListBinding
import com.tashuseyin.marvel.presentation.bindingadapter.BindingFragment
import com.tashuseyin.marvel.presentation.ui.marvel_character_list.adapter.MarvelCharacterAdapter
import com.tashuseyin.marvel.presentation.ui.marvel_character_list.viewmodel.MarvelCharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MarvelCharacterList : BindingFragment<FragmentMarvelCharacterListBinding>() {
    private val marvelViewModel: MarvelCharacterViewModel by viewModels()
    private lateinit var adapter: MarvelCharacterAdapter

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentMarvelCharacterListBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeViewModel()
    }

    private fun setAdapter() {
        adapter = MarvelCharacterAdapter()
        binding.recyclerview.adapter = adapter
        adapter.onItemClickListener = { characterId ->
            navigateDetailFragment(characterId)
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            marvelViewModel.marvelCharacterList.collect { data ->
                adapter.submitData(data)
            }
        }
    }

    private fun navigateDetailFragment(characterId: Int) {
        findNavController().navigate(
            MarvelCharacterListDirections.actionMarvelCharacterListToMarvelCharacterDetail(
                characterId
            )
        )
    }
}