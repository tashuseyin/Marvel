package com.tashuseyin.marvel.presentation.ui.marvel_character_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.viewbinding.ViewBinding
import com.tashuseyin.marvel.R
import com.tashuseyin.marvel.databinding.FragmentMarvelCharacterListBinding
import com.tashuseyin.marvel.presentation.bindingadapter.BindingFragment
import com.tashuseyin.marvel.presentation.ui.marvel_character_list.adapter.MarvelCharacterAdapter
import com.tashuseyin.marvel.presentation.ui.marvel_character_list.viewmodel.MarvelCharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MarvelCharacterList : BindingFragment<FragmentMarvelCharacterListBinding>() {
    private val marvelViewModel: MarvelCharacterViewModel by viewModels()
    private lateinit var adapter: MarvelCharacterAdapter

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentMarvelCharacterListBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        observeViewModel()
    }

    private fun setUpAdapter() {
        adapter = MarvelCharacterAdapter()
        binding.recyclerview.adapter = adapter
        adapter.onItemClickListener = { characterId ->
            navigateDetailFragment(characterId)
        }

        adapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {

                if (adapter.snapshot().isEmpty()) {
                    binding.progressBar.isVisible = true
                }
                binding.errorText.isVisible = false

            } else {
                binding.progressBar.isVisible = false

                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error

                    else -> null
                }
                error?.let {
                    if (adapter.snapshot().isEmpty()) {
                        binding.errorText.isVisible = true
                        binding.errorText.text = getString(R.string.error)
                    }
                }

            }
        }
    }

    private fun observeViewModel() {
        marvelViewModel.marvelCharacterList.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
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