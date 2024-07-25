package com.route.domain.common

sealed class ResultWrapper<out T> {

    data object Loading : ResultWrapper<Nothing>()

    data class Success<Type>(val data: Type) : ResultWrapper<Type>()

    data class Failure(val e: Throwable) : ResultWrapper<Nothing>()
}