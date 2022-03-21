package hu.alexujvary.pokeapitest.interfaces

import io.reactivex.disposables.Disposable

interface DisposableStore {
    fun add(disposable: Disposable)
    fun dispose()
}