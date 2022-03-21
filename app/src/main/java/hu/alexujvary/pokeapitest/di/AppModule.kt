package hu.alexujvary.pokeapitest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.alexujvary.pokeapitest.application.PokeApiTestApp
import hu.alexujvary.pokeapitest.network.RestApi
import hu.alexujvary.pokeapitest.network.RetrofitService
import javax.inject.Singleton

@Module
class AppModule(private val appContext: PokeApiTestApp) {

    @Provides
    fun providesContext(): Context {
        return appContext
    }

    @Provides
    @Singleton
    fun providesRetrofitService(): RetrofitService {
        return RetrofitService.getInstance()
    }

    @Provides
    @Singleton
    fun providesRestApi(): RestApi {
        return RetrofitService.getInstance().getRestApi()
    }
}