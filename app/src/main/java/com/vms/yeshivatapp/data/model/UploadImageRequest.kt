package com.vms.yeshivatapp.data.model

data class UploadImageRequest(
    val archivo: String,
    val nombre: String
)

data class ResponseUploadImage(
    val dat: String,
    val function: String,
    val date: Long,
    val status: String,
    val detail: String
)
