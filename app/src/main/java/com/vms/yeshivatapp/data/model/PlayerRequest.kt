package com.vms.yeshivatapp.data.model

import com.google.gson.annotations.SerializedName

data class PlayerRequest(
    @SerializedName("id_equipo")
    var id_equipo: Int
)
