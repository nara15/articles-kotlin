package com.example.articles_kotlin.network

import com.example.articles_kotlin.models.ArticlesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface NetworkAPIInterface {
    @GET("articles/index.android.json")
    fun fetchAllArticles(): Deferred<Response<ArticlesResponse>>
}