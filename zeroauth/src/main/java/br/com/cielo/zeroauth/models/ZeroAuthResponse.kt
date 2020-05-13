package br.com.cielo.zeroauth.models

import com.google.gson.annotations.SerializedName

data class ZeroAuthResponse (
    @SerializedName("Valid")
    val valid: Boolean,

    @SerializedName("ReturnCode")
    val returnCode: String,

    @SerializedName("ReturnMessage")
    val returnMessage: String,

    @SerializedName("IssuerTransactionId")
    val issuerTransactionId: String
)