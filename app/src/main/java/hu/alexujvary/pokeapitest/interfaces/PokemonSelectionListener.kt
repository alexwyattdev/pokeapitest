package hu.alexujvary.pokeapitest.interfaces

import hu.alexujvary.pokeapitest.model.PokemonItem

interface PokemonSelectionListener {
    fun pokemonSelected(pokemonItem: PokemonItem)
}