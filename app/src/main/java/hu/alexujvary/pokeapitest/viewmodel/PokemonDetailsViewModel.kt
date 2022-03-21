package hu.alexujvary.pokeapitest.viewmodel

import androidx.lifecycle.MutableLiveData
import hu.alexujvary.pokeapitest.enums.ErrorEnum
import hu.alexujvary.pokeapitest.extensions.add
import hu.alexujvary.pokeapitest.model.PokemonDetailsResponse
import hu.alexujvary.pokeapitest.repository.PokemonRepository
import hu.alexujvary.pokeapitest.util.DataState
import hu.alexujvary.pokeapitest.util.ErrorWrapper
import hu.alexujvary.pokeapitest.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class PokemonDetailsViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) : BaseViewModel() {

    val pokemonDetailsDataState = MutableLiveData<DataState<PokemonDetailsResponse>>()
    var pokemonId: Int = 0
    var pokemonName: String? = null

    fun getPokemonDetails() {
        pokemonDetailsDataState.postValue(DataState.Loading(true))

        pokemonRepository.getPokemonDetails(pokemonId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->

                pokemonDetailsDataState.postValue(DataState.Success(response))
            }, { t: Throwable? ->

                Timber.e(t)
                pokemonDetailsDataState.postValue(DataState.Error(ErrorWrapper(ErrorEnum.POKEMON_DETAILS_LOAD_ERROR)))
            }).add(this)
    }
}