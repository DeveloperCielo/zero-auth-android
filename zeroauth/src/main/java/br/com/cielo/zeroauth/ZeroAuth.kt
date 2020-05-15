package br.com.cielo.zeroauth

import br.com.braspag.cieloecommerceoauth.network.HttpCredentialsClient
import br.com.cielo.zeroauth.internal.network.ZeroAuthClient
import br.com.cielo.zeroauth.models.ClientResult
import br.com.cielo.zeroauth.models.ErrorResponse
import br.com.cielo.zeroauth.models.ZeroAuthRequest
import br.com.cielo.zeroauth.models.ZeroAuthResponse
import br.com.braspag.cieloecommerceoauth.network.Environment as OAuthEnvironment

class ZeroAuth(
    private val merchantId: String,
    private val clientId: String,
    private val clientSecret: String,
    private val environment: Environment = Environment.SANDBOX
) {
    fun validate(
        zeroAuthRequest: ZeroAuthRequest,
        callback: (ClientResult<ZeroAuthResponse>) -> Unit
    ) {
        val oAuthEnvironment = if (environment == Environment.PRODUCTION) {
            OAuthEnvironment.PRODUCTION
        } else {
            OAuthEnvironment.SANDBOX
        }

        val oAuthClient = HttpCredentialsClient(
            oAuthEnvironment,
            clientId,
            clientSecret
        )

        oAuthClient.getOAuthCredentials({
            val client = ZeroAuthClient(this.merchantId, this.environment)
            client.validate(it.token, zeroAuthRequest, callback)
        }, {
            callback.invoke(
                ClientResult(
                    result = null,
                    statusCode = HttpStatusCode.Unknown,
                    errors = listOf(
                        ErrorResponse(code = null, message = it)
                    )
                )
            )
        })
    }
}