package com.vms.yeshivatapp.data.network

import com.vms.yeshivatapp.data.adapter.PartidoDetalleResponse
import com.vms.yeshivatapp.data.model.*
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

    @Headers("Content-Type: application/json")
    @POST("jugadores/getPlayersPerTeam")
    suspend fun getInfoPlayers(@Body playerResuest: PlayerRequest): Response<JsonResponse>

    @Headers("Content-Type: application/json")
    @GET("equipos/getAllStatics")
    suspend fun getResultados():Response<EstadisticaEquipo>


    @Headers("Content-Type: application/json")
    @GET("partidos/getAll")
    suspend fun getGames():Response<PartidoDetalleResponse>
    @Headers("Content-Type: application/json")
    @POST("equipos/createTeam")
    suspend fun RegisterTeamS(@Body loginreques: RequestRegisterTeam): Response<ResponseRegisterTeam>
     @Headers("Content-Type: application/json")
     @POST("registro/uploadBase64")
     suspend fun RegisterImage(@Body uploadImage: UploadImageRequest): Response<ResponseUploadImage>


}