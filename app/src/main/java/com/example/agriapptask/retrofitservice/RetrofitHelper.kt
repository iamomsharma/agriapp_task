package com.example.agriapptask.retrofitservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val BASE_URL = "https://meme-api.com"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

}