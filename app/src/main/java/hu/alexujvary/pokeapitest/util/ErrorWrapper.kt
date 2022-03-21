package hu.alexujvary.pokeapitest.util

import hu.alexujvary.pokeapitest.enums.ErrorEnum

data class ErrorWrapper(var errorType: ErrorEnum = ErrorEnum.GENERAL, var errorMessage: String? = null)