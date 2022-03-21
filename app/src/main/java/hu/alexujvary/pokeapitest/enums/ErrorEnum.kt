package hu.alexujvary.pokeapitest.enums

import androidx.annotation.StringRes
import hu.alexujvary.pokeapitest.R

enum class ErrorEnum(@StringRes val errorResId: Int) {
    GENERAL(R.string.general_error),
    POKEMON_LIST_LOAD_ERROR(R.string.pokemon_list_load_error),
    POKEMON_DETAILS_LOAD_ERROR(R.string.pokemon_details_load_error),
    NO_INTERNET(R.string.no_internet)
}