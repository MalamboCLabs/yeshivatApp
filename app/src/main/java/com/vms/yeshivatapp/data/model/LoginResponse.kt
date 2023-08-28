package com.vms.yeshivatapp.data.model

data class LoginResponse(
    val data: Any,
    val function: String,
    val date: Long,
    val status: String,
    val detail: String
)
data class Data(
    val error: Int,
    val bloqueado: Int,
    val id_usuario: Int,
    val nombre_usuario: String,
    val correo: String,
    val mensaje: String,
    val id_perfil: Int,
    val nombre_perfil: String,
    val id_sesion: String
)