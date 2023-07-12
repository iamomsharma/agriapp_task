package com.example.agriapptask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.agriapptask.repository.PhotoRepository

class PhotoViewModelFactory(private val repository: PhotoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return PhotoViewModel(repository) as T
    }
}