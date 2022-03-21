package hu.alexujvary.pokeapitest.util

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val error: ErrorWrapper) : DataState<Nothing>()
    data class Loading(val isLoading: Boolean) : DataState<Nothing>()
}