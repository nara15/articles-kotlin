package com.example.articles_kotlin.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.articles_kotlin.models.Article
import com.example.articles_kotlin.network.NetworkAPIService
import com.example.articles_kotlin.repository.ArticlesRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class HomeViewModel : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    private val articlesRepository = ArticlesRepository(NetworkAPIService.shared)

    private val _text = MutableLiveData<String>().apply {
        value = "Loading articles..."
    }
    val text: LiveData<String> = _text

    val articlesLiveData = MutableLiveData<MutableList<Article>>()

    fun getAllArticles() {
        scope.launch {
            val latestArticles = articlesRepository.getAllArticles()
            articlesLiveData.postValue(latestArticles)
        }
    }
    fun cancelRequest() = coroutineContext.cancel()
}