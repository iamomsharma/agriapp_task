package com.example.agriapptask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agriapptask.model.PhotoModel
import com.example.agriapptask.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotoViewModel(private val photoRepository: PhotoRepository) : ViewModel()
{
    init {
        viewModelScope.launch(Dispatchers.IO) {
            photoRepository.getPhoto()
        }
    }

    val photos : LiveData<PhotoModel>
        get() = photoRepository.photoLiveData

    fun onRefreshClicked(){
        viewModelScope.launch(Dispatchers.IO) { // perform task on background thread
                photoRepository.getPhoto()
        }
    }
}