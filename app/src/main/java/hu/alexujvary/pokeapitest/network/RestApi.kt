package hu.alexujvary.pokeapitest.network

import hu.alexujvary.pokeapitest.application.Constants
import hu.alexujvary.pokeapitest.model.PokemonApiResponse
import hu.alexujvary.pokeapitest.model.PokemonDetailsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {
    @GET(Constants.POKEMON_ENDPOINT)
    fun getPokemonList(
        @Query("limit")
        limit: Int = Constants.POKEMON_LIST_LIMIT,
        @Query("offset")
        listOffset: Int,
    ): Single<PokemonApiResponse>

    @GET(Constants.POKEMON_ENDPOINT + "/{id}")
    fun getPokemonDetails(
        @Path("id")
        pokemonId: Int = 0
    ): Single<PokemonDetailsResponse>
}