package com.vms.yeshivatapp.data.network

import com.vms.yeshivatapp.data.model.Equipo
import com.vms.yeshivatapp.data.model.LoginRequest
import com.vms.yeshivatapp.data.model.LoginResponse
import com.vms.yeshivatapp.data.model.RespuestaEquipos
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {
    @Headers("Content-Type: application/json")
    @POST("sesion/logIn")

    suspend fun loginUser(@Body loginreques: LoginRequest): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @GET("equipos/getAll")
    suspend fun getTeams():Response<RespuestaEquipos>
}