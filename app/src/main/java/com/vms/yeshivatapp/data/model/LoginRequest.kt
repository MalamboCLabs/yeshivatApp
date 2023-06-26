package com.vms.yeshivatapp.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("alias")
    var alias: String,
    @SerializedName("contrasena")
    var contrasena: String,
    @SerializedName("sentido")
    var sentido: String,
    @SerializedName("id")
    var ip: String,
    @SerializedName("sistema_operativo")
    var sistema_operativos: String,
    @SerializedName("navegador")
    var navegador: String
)
