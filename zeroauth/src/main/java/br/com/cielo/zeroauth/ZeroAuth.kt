package br.com.cielo.zeroauth

import br.com.cielo.zeroauth.internal.network.ZeroAuthClient
import br.com.cielo.zeroauth.models.ClientResult
import br.com.cielo.zeroauth.models.ZeroAuthRequest
import br.com.cielo.zeroauth.models.ZeroAuthResponse

class ZeroAuth(private val merchantId: String, private val environment: Environment = Environment.SANDBOX) {
    fun validate(accessToken: String, zeroAuthRequest: ZeroAuthRequest, callback: (ClientResult<ZeroAuthResponse>) -> Unit) {
        val client = ZeroAuthClient(this.merchantId, this.environment)

        client.validate(accessToken, zeroAuthRequest, callback)
    }
}