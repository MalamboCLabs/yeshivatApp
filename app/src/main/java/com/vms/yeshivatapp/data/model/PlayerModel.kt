package com.vms.yeshivatapp.data.model

data class Jugador(
    val id_jugador: Int,
    val id_equipo: Int,
    val nombre_completo: String,
    val apodo: String,
    val edad: Int,
    val escolaridad: String,
    val goles_ultima_temporada: Int?, // Puedes usar Int? si los valores pueden ser nulos
    val goles_total_liga: Int?, // Puedes usar Int? si los valores pueden ser nulos
    val posicion: String,
    val foto: String
)

data class JsonResponse(
    val data: List<Jugador>,
    val function: String,
    val date: Long,
    val status: String,
    val detail: String
)
