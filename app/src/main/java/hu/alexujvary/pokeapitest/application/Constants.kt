package hu.alexujvary.pokeapitest.application

interface Constants {

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val POKEMON_ENDPOINT = "pokemon"

        //for pagination
        const val POKEMON_LIST_LIMIT = 100

        //to be able to show images on the main list page
        const val IMAGE_BASE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
        const val IMAGE_EXTENSION = ".png"

        const val POKEMON_ID = "POKEMON_ID"
        const val POKEMON_NAME = "POKEMON_NAME"
    }
}