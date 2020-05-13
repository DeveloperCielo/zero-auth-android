package br.com.cielo.zeroauth.models

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("Code")
    val code: String? = null,

    @SerializedName("Message")
    val message: String? = null
)