package br.com.cielo.zeroauth.models

import br.com.cielo.zeroauth.HttpStatusCode
import okhttp3.ResponseBody

data class ClientResult <T>(
    var result: T?,
    var statusCode: HttpStatusCode,
    var errors: List<ErrorResponse?> = listOf()
)