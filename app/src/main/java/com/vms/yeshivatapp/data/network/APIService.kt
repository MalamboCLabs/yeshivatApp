package com.vms.yeshivatapp.data.network

import com.vms.yeshivatapp.data.model.LoginRequest
import com.vms.yeshivatapp.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {
    @Headers("Content-Type: application/json")
    @POST("sesion/logIn")

    suspend fun loginUser(@Body loginreques: LoginRequest): Response<LoginResponse>
}