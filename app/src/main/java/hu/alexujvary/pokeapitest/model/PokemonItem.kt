package hu.alexujvary.pokeapitest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonItem(
    @SerializedName("name")
    var pokemonName: String? = null,
    var url: String? = null
) : Serializable