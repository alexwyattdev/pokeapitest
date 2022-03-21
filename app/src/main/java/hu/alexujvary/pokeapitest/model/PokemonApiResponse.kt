package hu.alexujvary.pokeapitest.model

import com.google.gson.annotations.SerializedName

data class PokemonApiResponse(
    @SerializedName("count")
    var totalResults: Int = 0,
    @SerializedName("results")
    var pokemons: List<PokemonItem> = listOf()
)