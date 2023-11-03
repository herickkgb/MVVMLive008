package com.herick.mvvmlive008.rest

import com.herick.mvvmlive008.models.Live
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("lista-lives.json")
    fun getAllLives(): Call<List<Live>>

    companion object {
        private val retrofitService: RetrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://s3.amazonaws.com/api.ocanha.com/") // Corrigindo a URL base
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)
        }

        fun getIntance(): RetrofitService {
            return retrofitService
        }
    }


}