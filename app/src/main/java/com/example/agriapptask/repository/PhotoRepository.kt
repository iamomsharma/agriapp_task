package com.example.agriapptask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.agriapptask.model.PhotoModel
import com.example.agriapptask.retrofitservice.ApiService
import retrofit2.Response

class PhotoRepository(private val apiService : ApiService) {

    private val photoMutableLiveData = MutableLiveData<PhotoModel>()

    val photoLiveData : LiveData<PhotoModel>
        get() = photoMutableLiveData

    suspend fun getPhoto(){

        val result = apiService.getPhoto()
        if (result.body() != null)
        {
            photoMutableLiveData.postValue(result.body())
        }
    }

}