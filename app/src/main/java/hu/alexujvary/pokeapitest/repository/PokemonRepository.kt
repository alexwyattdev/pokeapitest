package hu.alexujvary.pokeapitest.repository

import hu.alexujvary.pokeapitest.model.PokemonApiResponse
import hu.alexujvary.pokeapitest.model.PokemonDetailsResponse
import hu.alexujvary.pokeapitest.network.RestApi
import io.reactivex.Single
import javax.inject.Inject

class PokemonRepository @Inject internal constructor(private val restApi: RestApi) {

    fun getPokemonList(offset: Int): Single<PokemonApiResponse> {
        return restApi.getPokemonList(listOffset = offset)
    }

    fun getPokemonDetails(pokemonId: Int): Single<PokemonDetailsResponse> {
        return restApi.getPokemonDetails(pokemonId)
    }
}