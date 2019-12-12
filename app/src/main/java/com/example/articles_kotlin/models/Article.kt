package com.example.articles_kotlin.models

data class Article(
    val article_date: String,
    val author: String,
    val description: String,
    val image: String,
    val link: String,
    val title: String,
    val uuid: String
)