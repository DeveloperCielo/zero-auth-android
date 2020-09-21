package br.com.cielo.zeroauth.internal.network

import br.com.cielo.zeroauth.BuildConfig
import br.com.cielo.zeroauth.*
import br.com.cielo.zeroauth.models.ClientResult
import br.com.cielo.zeroauth.models.ErrorResponse
import br.com.cielo.zeroauth.models.ZeroAuthRequest
import br.com.cielo.zeroauth.models.ZeroAuthResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class ZeroAuthClient (private val merchantId: String, private val environment: Environment = Environment.SANDBOX) {

    internal fun validate(
        token: String,
        request: ZeroAuthRequest,
        callback: (model: ClientResult<ZeroAuthResponse>) -> Unit
    ) {
        val webClient = WebClient(getEnvironmentUrl(environment))

        val xSdkVersion = BuildConfig.X_SDK_VERSION

        val call = webClient.createService(ZeroAuthApi::class.java).validate(token.beared(), merchantId, xSdkVersion, request)

        call.enqueue(object : Callback<ZeroAuthResponse> {
            override fun onFailure(call: Call<ZeroAuthResponse>, t: Throwable) {
                t.localizedMessage?.let {
                    callback.invoke(
                        ClientResult(
                            result = null,
                            statusCode = HttpStatusCode.Unknown,
                            errors = listOf(
                                ErrorResponse(
                                    code = null,
                                    message = it
                                )
                            )
                        )
                    )
                }
            }

            override fun onResponse(
                call: Call<ZeroAuthResponse>,
                responseCielo: Response<ZeroAuthResponse>
            ) {
                when (responseCielo.isSuccessful) {
                    true -> {
                        callback.invoke(
                            ClientResult(
                                result = responseCielo.body(),
                                statusCode = responseCielo.code().toStatusCode()
                            )
                        )
                    }

                    false -> callback.invoke(ClientResult(
                        result = null,
                        statusCode = responseCielo.code().toStatusCode(),
                        errors = Gson().fromJson(responseCielo.errorBody()?.string(), Array<ErrorResponse>::class.java).toList()
                    ))
                }
            }
        })
    }

    private fun getEnvironmentUrl(environment: Environment): String {
        return if (environment == Environment.SANDBOX)
            "https://apisandbox.cieloecommerce.cielo.com.br/"
        else
            "https://api.cieloecommerce.cielo.com.br/"
    }

    private fun String.beared() : String {
        return "Bearer $this"
    }
}