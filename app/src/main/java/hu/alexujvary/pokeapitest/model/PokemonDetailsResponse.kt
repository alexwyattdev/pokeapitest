package hu.alexujvary.pokeapitest.model

import com.google.gson.annotations.SerializedName
import timber.log.Timber
import java.io.Serializable

data class PokemonDetailsResponse(
    var abilities: List<PokemonAbilityItem> = listOf(),
    var height: Int? = null,
    var weight: Int? = null
) : Serializable

data class PokemonAbilityItem(
    var ability: PokemonAbility? = null,
    @SerializedName("is_hidden")
    var isHidden: Boolean = false,
) : Serializable

data class PokemonAbility(
    @SerializedName("name")
    var abilityName: String? = null
) : Serializable