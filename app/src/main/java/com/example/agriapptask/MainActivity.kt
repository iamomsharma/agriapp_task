package com.example.agriapptask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
  //  private var apiService: ApiService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initialization()

    }

    private fun initialization() {

        callPhotoApi()

        binding.topRefreshLayout.setOnRefreshListener {

            binding.topRefreshLayout.isRefreshing = false

            callPhotoApi()
        }

        binding.bb.setOnClickListener{
            callPhotoApi()
        }

    }

    private fun callPhotoApi() {

        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val photoRepository = PhotoRepository(apiService)
        val photoViewModel = ViewModelProvider(this, PhotoViewModelFactory(photoRepository))[PhotoViewModel::class.java]

        photoViewModel.photos.observe(this, Observer {
            setData(it)
        })
    }

    private fun setData(photoModel: PhotoModel) {
        binding.tvTitle.text = photoModel.title
        Glide.with(this)
            .load(photoModel.url)
            .into(binding.ivPhoto)
    }
}