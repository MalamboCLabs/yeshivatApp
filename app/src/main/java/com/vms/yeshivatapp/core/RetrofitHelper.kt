package com.vms.yeshivatapp.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("http://167.71.22.22/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}