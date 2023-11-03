package com.herick.mvvmlive008

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.herick.mvvmlive008.ViewModel.main.MainViewModel
import com.herick.mvvmlive008.ViewModel.main.MainViewModelFactory
import com.herick.mvvmlive008.adapters.MainAdapter
import com.herick.mvvmlive008.databinding.ActivityMainBinding
import com.herick.mvvmlive008.repositores.MainRepository
import com.herick.mvvmlive008.rest.RetrofitService
import java.net.URI

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getIntance()

    private val adapter = MainAdapter {
        openLink(it.link)
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

    }

    override fun onStart() {
        super.onStart()

        viewModel.liveList.observe(this, Observer { lives ->
            Log.i("Herick", "onStart")
            adapter.setLiveList(lives)
        })

        viewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllLives()
    }

    private fun openLink(link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}