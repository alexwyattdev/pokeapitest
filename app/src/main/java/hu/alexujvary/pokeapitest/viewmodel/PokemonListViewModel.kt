package hu.alexujvary.pokeapitest.viewmodel

import androidx.lifecycle.MutableLiveData
import hu.alexujvary.pokeapitest.application.Constants
import hu.alexujvary.pokeapitest.enums.ErrorEnum
import hu.alexujvary.pokeapitest.extensions.add
import hu.alexujvary.pokeapitest.model.PokemonItem
import hu.alexujvary.pokeapitest.repository.PokemonRepository
import hu.alexujvary.pokeapitest.util.DataState
import hu.alexujvary.pokeapitest.util.ErrorWrapper
import hu.alexujvary.pokeapitest.util.NetworkCheck
import hu.alexujvary.pokeapitest.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class PokemonListViewModel @Inject constructor(private val pokemonRepository: PokemonRepository, private val networkCheck: NetworkCheck) : BaseViewModel() {

    val pokemonListDataState = MutableLiveData<DataState<Pair<List<PokemonItem>, Boolean>>>()
    private var offset = 0
    private var totalResults = 0

    init {
        getPokemonList(newOffSet = offset)
    }

    fun reload() {
        offset = 0
        getPokemonList(newOffSet = offset)
    }

    fun loadNextPage() {
        val newOffset = offset + Constants.POKEMON_LIST_LIMIT
        if (newOffset <= totalResults) {
            getPokemonList(newPageLoad = true, newOffset)
        }
    }

    private fun getPokemonList(newPageLoad: Boolean = false, newOffSet: Int = 0) {
        if (networkCheck.hasConnection()) {
            pokemonListDataState.postValue(DataState.Loading(true))

            pokemonRepository.getPokemonList(newOffSet)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    offset = newOffSet
                    totalResults = response.totalResults
                    pokemonListDataState.postValue(DataState.Success(Pair(response.pokemons, newPageLoad)))
                }, { t: Throwable? ->

                    Timber.e(t)
                    pokemonListDataState.postValue(DataState.Error(ErrorWrapper(ErrorEnum.POKEMON_LIST_LOAD_ERROR)))
                }).add(this)
        } else {
            pokemonListDataState.postValue(DataState.Error(ErrorWrapper(ErrorEnum.NO_INTERNET)))
        }
    }
}