package com.example.agriapptask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.agriapptask.databinding.ActivityMainBinding
import com.example.agriapptask.model.PhotoModel
import com.example.agriapptask.repository.PhotoRepository
import com.example.agriapptask.retrofitservice.ApiService
import com.example.agriapptask.retrofitservice.RetrofitHelper
import com.example.agriapptask.viewmodel.PhotoViewModel
import com.example.agriapptask.viewmodel.PhotoViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var photoViewModel: PhotoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // init the viewmodel
        setupViewModel()

        // observe live data
        setupObservers()

        // setup Refresh listener
        setupRefreshLayout()

    }

    private fun setupObservers() {
        photoViewModel.photos.observe(this, Observer { it
            setData(it)
        })
    }

    private fun setupRefreshLayout() {

        binding.topRefreshLayout.setOnRefreshListener {

            binding.topRefreshLayout.isRefreshing = false
            photoViewModel.onRefreshClicked()
        }
    }

    private fun setupViewModel() {
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val photoRepository = PhotoRepository(apiService)
        photoViewModel = ViewModelProvider(
            this,
            PhotoViewModelFactory(photoRepository)
        ).get(PhotoViewModel::class.java)

    }

    private fun setData(photoModel: PhotoModel) {
        binding.tvTitle.text = photoModel.title
        Glide.with(this)
            .load(photoModel.url)
            .into(binding.ivPhoto)
    }
}