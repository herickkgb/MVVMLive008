package com.herick.mvvmlive008.repositores

import com.herick.mvvmlive008.rest.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllLives() = retrofitService.getAllLives()

}