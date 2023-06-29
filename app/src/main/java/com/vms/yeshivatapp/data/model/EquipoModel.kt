package com.vms.yeshivatapp.data.model

data class EquipoModel(
    val id:Int,
    val nombre:String,
    val logoEquipo: Int
)
data class Equipo(
    val id_equipo: Int,
    val nombre: String,
    val logo: String?,
    val foto: String?,
    val descripcion: String
)

data class RespuestaEquipos(
    val data: List<Equipo>,
    val function: String,
    val date: Long,
    val status: String,
    val detail: String
)

