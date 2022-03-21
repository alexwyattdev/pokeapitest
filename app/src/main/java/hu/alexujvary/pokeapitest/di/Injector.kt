package hu.alexujvary.pokeapitest.di

import hu.alexujvary.pokeapitest.application.PokeApiTestApp
import hu.alexujvary.pokeapitest.view.pokemondetails.PokemonDetailsFragment
import hu.alexujvary.pokeapitest.view.pokemonlist.PokemonListFragment

object Injector {

    private lateinit var appComponent: AppComponent

    fun init(application: PokeApiTestApp) {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(application)).build()
    }

    fun inject(forItem: PokemonListFragment) {
        appComponent.inject(forItem)
    }

    fun inject(forItem: PokemonDetailsFragment) {
        appComponent.inject(forItem)
    }
}