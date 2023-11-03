package com.herick.mvvmlive008

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.herick.mvvmlive008.ViewModel.main.MainViewModel
import com.herick.mvvmlive008.ViewModel.main.MainViewModelFactory
import com.herick.mvvmlive008.adapters.MainAdapter
import com.herick.mvvmlive008.databinding.ActivityMainBinding
import com.herick.mvvmlive008.repositores.MainRepository
import com.herick.mvvmlive008.rest.RetrofitService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getIntance()

    private val adapter = MainAdapter {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(
                MainRepository(retrofitService)
            )
        ).get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter


        #########################

        Parei no minuto 1:22:25

        #########################
    }
}