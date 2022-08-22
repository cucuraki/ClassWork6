package com.example.classwork6.responsestate

sealed class ResponseState<T>{
    data class Success<T>(val body: T) : ResponseState<T>()
    data class Error<T>(val errorMessage: String) : ResponseState<T>()
    class Load<T> : ResponseState<T>()
}