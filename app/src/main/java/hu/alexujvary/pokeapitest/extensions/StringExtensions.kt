package hu.alexujvary.pokeapitest.extensions

import timber.log.Timber

fun String?.capitalizeFirstChar(): String? {
    return try {
        this?.replaceFirstChar { it.uppercase() }
    } catch (e: Exception) {
        Timber.e(e)
        null
    }
}