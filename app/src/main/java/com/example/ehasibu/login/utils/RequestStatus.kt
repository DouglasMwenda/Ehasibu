package com.example.ehasibu.login.utils

sealed class RequestStatus<out T> {
    object waiting : RequestStatus<Nothing>()
    data class success<T>(val data: T) : RequestStatus<T>()
    data class Error(val message: HashMap<String, String>) : RequestStatus<Nothing>()

}