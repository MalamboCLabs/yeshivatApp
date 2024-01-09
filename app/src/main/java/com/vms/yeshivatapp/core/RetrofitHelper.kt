package com.vms.yeshivatapp.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("http://emunafutbol.com:3001/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}