package com.example.articles_kotlin.repository

import com.example.articles_kotlin.models.Article
import com.example.articles_kotlin.network.NetworkAPIInterface

class ArticlesRepository (private val apiInterface: NetworkAPIInterface): BaseRepository() {
    suspend fun getAllArticles(): MutableList<Article>? {
        return safeAPICall(call = {
            apiInterface.fetchAllArticles().await()
        }, error = "Error happened")?.articles?.toMutableList()
    }
}