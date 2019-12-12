package com.example.articles_kotlin.network

import java.lang.Exception

sealed class APIOutput <out T : Any> {
    data class Success<out T: Any>(val output : T) : APIOutput<T>()
    data class Error(val exception: Exception) : APIOutput<Nothing>()
}