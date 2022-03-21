package hu.alexujvary.pokeapitest.di

import dagger.Component
import hu.alexujvary.pokeapitest.view.pokemondetails.PokemonDetailsFragment
import hu.alexujvary.pokeapitest.view.pokemonlist.PokemonListFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(item: PokemonListFragment)
    fun inject(item: PokemonDetailsFragment)
}