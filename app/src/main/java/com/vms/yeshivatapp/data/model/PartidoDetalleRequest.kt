package com.vms.yeshivatapp.data.adapter

data class PartidoDetalleResponse(
    val data: List<TemporadaPartidos>,
    val function: String,
    val date: Long,
    val status: String,
    val detail: String
)

data class TemporadaPartidos(
    val temporada: String,
    val partidos: List<Partido>
)

data class Partido(
    val id_partido: Int,
    val en_favoritos: Boolean,
    val fecha: String,
    val equipos: String,
    val resultado: String,
    val verPartidoHabilitado: Boolean,
    val verDetallePartidoHabilitado: Boolean,
    val id_streaming: Int
)
