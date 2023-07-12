package com.example.agriapptask.retrofitservice

import com.example.agriapptask.model.PhotoModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/gimme")
    suspend fun getPhoto(): Response<PhotoModel>

}