package com.vms.yeshivatapp.data.model

import com.google.gson.annotations.SerializedName

data class RequestRegisterTeam(
    @SerializedName("nombre")
    var nombre: String,
    @SerializedName("logo")
    var logo: String,
    @SerializedName("foto")
    var foto: String,
    @SerializedName("descripcion")
    var descripcion: String,
    @SerializedName("id_capitan")
    var id_capitan: Int
)
