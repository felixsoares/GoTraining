package com.felix.gotraining.data.model

sealed class ResultState<out T> {
    object Loading : ResultState<Nothing>()
    object Error : ResultState<Nothing>()
    data class Success<T>(val data: T) : ResultState<T>()
}
