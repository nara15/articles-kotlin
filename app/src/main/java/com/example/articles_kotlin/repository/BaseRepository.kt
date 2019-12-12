package com.example.articles_kotlin.repository

import android.util.Log
import com.example.articles_kotlin.network.APIOutput
import retrofit2.Response
import java.io.IOException


open class BaseRepository {
    suspend fun <T: Any> safeAPICall(call: suspend() -> Response<T>, error: String) : T? {
        val response = apiOutPut(call, error)
        var output: T? = null

        when (response) {
            is APIOutput.Success ->
                output = response.output
            is APIOutput.Error -> Log.e("Error", "The $error and the ${response.exception}")
        }

        return output
    }

    private suspend fun<T : Any> apiOutPut(call: suspend()-> Response<T> , error: String) : APIOutput<T>{
        val response = call.invoke()
        return if (response.isSuccessful)
            APIOutput.Success(response.body()!!)
        else
            APIOutput.Error(IOException("OOps .. Something went wrong due to  $error"))
    }

}