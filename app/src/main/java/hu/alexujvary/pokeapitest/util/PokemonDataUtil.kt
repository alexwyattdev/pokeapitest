package hu.alexujvary.pokeapitest.util

import hu.alexujvary.pokeapitest.application.Constants
import hu.alexujvary.pokeapitest.model.PokemonAbilityItem
import timber.log.Timber

class PokemonDataUtil {

    companion object {

        //get the Pokemon id based on the id found in the url
        fun getIdFromUrl(url: String?): Int {
            return try {
                url?.replace(Constants.BASE_URL + Constants.POKEMON_ENDPOINT, "")?.replace("/", "")?.trim()?.toInt() ?: 0
            } catch (e: Exception) {
                Timber.e(e)
                0
            }
        }

        //get the Pokemon image's url based on the Pokemon's url and the baseImageUrl
        fun getImageUrlFromUrl(url: String?): String? {
            return try {
                Constants.IMAGE_BASE_URL + getIdFromUrl(url) + Constants.IMAGE_EXTENSION
            } catch (e: Exception) {
                Timber.e(e)
                null
            }
        }

        //get the Pokemon image's url based on the Pokemon's id and the baseImageUrl
        fun getImageUrlFromId(pokemonId: Int?): String? {
            return try {
                Constants.IMAGE_BASE_URL + (pokemonId ?: 0) + Constants.IMAGE_EXTENSION
            } catch (e: Exception) {
                Timber.e(e)
                null
            }
        }

        //retrieve the list of nonHidden abilities as String List
        fun getNonHiddenAbilityNamesList(abilities: List<PokemonAbilityItem>): List<String> {
            return try {
                abilities.filter { !it.isHidden }.mapNotNull { it.ability }.mapNotNull { it.abilityName }
            } catch (e: Exception) {
                Timber.e(e)
                listOf()
            }
        }

        fun getHeightInMeter(height: Int?): String = getDividedValueAsString(height)

        fun getWeightInKiloGram(weight: Int?): String = getDividedValueAsString(weight)

        private fun getDividedValueAsString(value: Int?): String {
            return try {
                String.format("%.1f", value?.div(10F) ?: 0F)
            } catch (e: Exception) {
                Timber.e(e)
                ""
            }
        }
    }
}