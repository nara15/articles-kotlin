package com.example.articles_kotlin.models

data class ArticlesResponse(
    val articles: List<Article>,
    val last_rev: String
)