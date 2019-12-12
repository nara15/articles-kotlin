package com.example.articles_kotlin.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkAPIService {

    private val interceptor = Interceptor { chain ->
        val url = chain.request().url().newBuilder().build()
        val request = chain.request().newBuilder().url(url).build()
        chain.proceed(request)
    }

    private val apiClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().client(apiClient)
            .baseUrl("https://www.beenverified.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val shared: NetworkAPIInterface = getRetrofit().create(NetworkAPIInterface::class.java)

}