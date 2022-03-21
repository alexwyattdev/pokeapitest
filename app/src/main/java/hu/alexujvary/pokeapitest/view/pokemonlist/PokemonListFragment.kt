package hu.alexujvary.pokeapitest.view.pokemonlist

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import hu.alexujvary.pokeapitest.R
import hu.alexujvary.pokeapitest.application.Constants
import hu.alexujvary.pokeapitest.databinding.FragmentPokemonListBinding
import hu.alexujvary.pokeapitest.di.Injector
import hu.alexujvary.pokeapitest.extensions.addFabScrollToTop
import hu.alexujvary.pokeapitest.extensions.fadeOut
import hu.alexujvary.pokeapitest.extensions.smoothAndFastScrollToPosition
import hu.alexujvary.pokeapitest.interfaces.PokemonSelectionListener
import hu.alexujvary.pokeapitest.model.PokemonItem
import hu.alexujvary.pokeapitest.util.DataState
import hu.alexujvary.pokeapitest.util.EndlessRecyclerOnScrollListener
import hu.alexujvary.pokeapitest.util.PokemonDataUtil
import hu.alexujvary.pokeapitest.view.base.BaseFragment
import hu.alexujvary.pokeapitest.viewmodel.PokemonListViewModel
import javax.inject.Inject

class PokemonListFragment : BaseFragment(R.layout.fragment_pokemon_list) {

    @Inject
    lateinit var pokemonListViewModel: PokemonListViewModel

    private val binding: FragmentPokemonListBinding by viewBinding()

    private lateinit var adapter: PokemonListAdapter

    override fun injectDependencies() {
        Injector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupViewModelObservers()
    }

    private fun setupAdapter() {
        adapter = PokemonListAdapter(requireContext(), mutableListOf(), object : PokemonSelectionListener {
            override fun pokemonSelected(pokemonItem: PokemonItem) {
                navigateToDetails(pokemonItem)
            }
        })
    }

    private fun setupViews() {
        progressLayout = binding.progress.progressLayout
        swipeRefreshLayout = binding.swipeContainer

        binding.rvPokemons.adapter = adapter

        binding.fabScrollToTop.hide()

        binding.fabScrollToTop.setOnClickListener {
            binding.rvPokemons.smoothAndFastScrollToPosition(0)
        }

        binding.rvPokemons.addFabScrollToTop(binding.fabScrollToTop)

        binding.swipeContainer.setOnRefreshListener {
            pokemonListViewModel.reload()
        }

        binding.rvPokemons.addOnScrollListener(object : EndlessRecyclerOnScrollListener(binding.rvPokemons.layoutManager as GridLayoutManager) {
            override fun onLoadMore() {
                pokemonListViewModel.loadNextPage()
            }
        })
    }

    private fun setupViewModelObservers() {
        pokemonListViewModel.pokemonListDataState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DataState.Success<Pair<List<PokemonItem>, Boolean>> -> {
                    displayProgress(false)
                    displayError(null)
                    processData(state.data.first, state.data.second)
                }
                is DataState.Error -> {
                    displayProgress(false)
                    displayError(state.error)
                }
                is DataState.Loading -> {
                    displayProgress(state.isLoading)
                }
            }
        }
    }

    private fun processData(pokemonList: List<PokemonItem>, isPagingContent: Boolean) {
        if (!pokemonList.isNullOrEmpty()) {
            binding.ivPlaceholder.fadeOut()
        }
        adapter.update(pokemonList, isPagingContent)
    }

    private fun navigateToDetails(pokemonItem: PokemonItem) {
        val bundle = Bundle()
        bundle.putInt(Constants.POKEMON_ID, PokemonDataUtil.getIdFromUrl(pokemonItem.url))
        bundle.putString(Constants.POKEMON_NAME, pokemonItem.pokemonName)
        findNavController().navigate(R.id.navigate_to_details, bundle)
    }
}