package hu.alexujvary.pokeapitest.application

import android.app.Application
import hu.alexujvary.pokeapitest.di.Injector
import timber.log.Timber

class PokeApiTestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
        Timber.plant(Timber.DebugTree())
    }
}