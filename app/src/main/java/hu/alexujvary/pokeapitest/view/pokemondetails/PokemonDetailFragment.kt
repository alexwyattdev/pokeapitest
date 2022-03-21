package hu.alexujvary.pokeapitest.view.pokemondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import hu.alexujvary.pokeapitest.R
import hu.alexujvary.pokeapitest.application.Constants
import hu.alexujvary.pokeapitest.databinding.FragmentPokemonDetailsBinding
import hu.alexujvary.pokeapitest.di.Injector
import hu.alexujvary.pokeapitest.extensions.capitalizeFirstChar
import hu.alexujvary.pokeapitest.extensions.fadeOut
import hu.alexujvary.pokeapitest.extensions.visible
import hu.alexujvary.pokeapitest.model.PokemonDetailsResponse
import hu.alexujvary.pokeapitest.util.DataState
import hu.alexujvary.pokeapitest.util.PokemonDataUtil
import hu.alexujvary.pokeapitest.view.base.BaseFragment
import hu.alexujvary.pokeapitest.viewmodel.PokemonDetailsViewModel
import javax.inject.Inject

class PokemonDetailsFragment : BaseFragment(R.layout.fragment_pokemon_details) {

    @Inject
    lateinit var pokemonDetailsViewModel: PokemonDetailsViewModel

    private val binding: FragmentPokemonDetailsBinding by viewBinding()

    override fun injectDependencies() {
        Injector.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pokemonDetailsViewModel.pokemonId = arguments?.getInt(Constants.POKEMON_ID) ?: 0
        pokemonDetailsViewModel.pokemonName = arguments?.getString(Constants.POKEMON_NAME)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupViewModelObservers()
        loadData()
    }

    private fun setupViews() {
        pokemonDetailsViewModel.pokemonName?.let {
            binding.toolbarTitle.text = getString(R.string.pokedex_id, it.capitalizeFirstChar(), pokemonDetailsViewModel.pokemonId)
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        progressLayout = binding.progress.progressLayout
    }

    private fun setupViewModelObservers() {
        pokemonDetailsViewModel.pokemonDetailsDataState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DataState.Success<PokemonDetailsResponse> -> {
                    displayProgress(false)
                    updateViewsWithData(state.data)
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

    private fun loadData() {
        pokemonDetailsViewModel.getPokemonDetails()
    }

    private fun updateViewsWithData(pokemonDetailsResponse: PokemonDetailsResponse) {
        binding.ivPlaceholder.fadeOut()
        binding.svPokemonDetailsContent.visible()

        Glide.with(requireContext()).load(PokemonDataUtil.getImageUrlFromId(pokemonDetailsViewModel.pokemonId)).centerInside().error(R.drawable.placeholder).placeholder(R.drawable.placeholder)
            .into(binding.ivPokemonDetailsImage)

        binding.tvPokemonHeight.text = getString(R.string.height_meter, PokemonDataUtil.getHeightInMeter(pokemonDetailsResponse.height))
        binding.tvPokemonWeight.text = getString(R.string.weight_kg, PokemonDataUtil.getWeightInKiloGram(pokemonDetailsResponse.weight))

        binding.rvAbilities.adapter = PokemonAbilitiesAdapter(PokemonDataUtil.getNonHiddenAbilityNamesList(pokemonDetailsResponse.abilities))
    }
}