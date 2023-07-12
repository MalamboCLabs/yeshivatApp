package com.vms.yeshivatapp.data.model

data class EstadisticaEquipo(
    val data: List<EquipoR>,
    val function: String,
    val date: Long,
    val status: String,
    val detail: String
)

data class EquipoR(
    val id_estadistica_equipo: Int,
    val equipo: String,
    val num_juegos: Int,
    val num_ganados: Int,
    val num_perdidos: Int,
    val num_goles: Int,
    val en_contra: Int
)