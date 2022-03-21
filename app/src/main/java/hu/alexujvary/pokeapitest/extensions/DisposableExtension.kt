package hu.alexujvary.pokeapitest.extensions

import hu.alexujvary.pokeapitest.interfaces.DisposableStore
import io.reactivex.disposables.Disposable

fun Disposable.add(disposableStore: DisposableStore) = disposableStore.add(this)