package br.com.cielo.zeroauth.models

import com.google.gson.Gson
import retrofit2.Response

object ErrorUtils {
    fun parseError(response: Response<*>): ErrorResponse? {
        return Gson().fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
    }
}