package com.tashuseyin.marvel.presentation.ui.marvel_character_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
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
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentMarvelCharacterListBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            marvelViewModel.state.collect { state ->
                if (state.characters.isNotEmpty()) {
                    val adapter = MarvelCharacterAdapter { characterId ->
                        findNavController().navigate(
                            MarvelCharacterListDirections.actionMarvelCharacterListToMarvelCharacterDetail(
                                characterId
                            )
                        )
                    }

                    adapter.setData(state.characters)
                    binding.recyclerview.adapter = adapter
                }
                if (state.error.isNotBlank()) {
                    binding.errorText.text = state.error
                }
                binding.progressBar.isVisible = state.isLoading
            }
        }
    }
}