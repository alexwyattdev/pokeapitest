package hu.alexujvary.pokeapitest.viewmodel.base

import androidx.lifecycle.ViewModel
import hu.alexujvary.pokeapitest.interfaces.DisposableStore
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel(), DisposableStore {

    private val compositeDisposable = CompositeDisposable()

    override fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }
}