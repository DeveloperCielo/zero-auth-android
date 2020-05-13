package br.com.cielo.zeroauth.internal.network

import br.com.cielo.zeroauth.models.ZeroAuthRequest
import br.com.cielo.zeroauth.models.ZeroAuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

internal interface ZeroAuthApi {
    @POST("1/zeroauth")
    fun validate(
        @Header("Authorization") authorization: String,
        @Header("MerchantId") merchantId: String,
        @Body request: ZeroAuthRequest,
        @Header("Content-Type") content_type: String = "application/json",
        @Header("Accept") accept: String = "application/json"
    ): Call<ZeroAuthResponse>
}