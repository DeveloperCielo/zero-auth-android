package br.com.cielo.zeroauthapp

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.com.cielo.zeroauth.Environment
import br.com.cielo.zeroauth.ZeroAuth
import br.com.cielo.zeroauth.models.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zeroAuth = ZeroAuth(
            merchantId = "<MERCHANT-ID>",
            environment = Environment.SANDBOX
        )

        zeroAuth.validate(
            accessToken = "<ACCESS-TOKEN>",
            zeroAuthRequest = ZeroAuthRequest(
                cardType = CardType.CREDIT_CARD,
                cardNumber = "1111222233334444",
                holder = "Maurici Ferreira Junior",
                expirationDate = "01/2030",
                securityCode = "123",
                saveCard = false,
                brand = "Visa",
                cardOnFile = CardOnFile(
                    usage = Usage.FIRST,
                    reason = Reason.RECURRING
                )
            )
        ) {
            Log.d("ZERO_AUTH", it.toString())

            if (it.result != null) {
                with(it.result!!) {
                    valid_result.text = valid.toString()
                    return_code.text = returnCode
                    return_message.text = returnMessage
                    issuer_transaction_id.text = issuerTransactionId
                }
            } else {
                valid_result.text = null
                return_code.text = null
                return_message.text = null
                issuer_transaction_id.text = null
            }

            if (it.errors.isNotEmpty()) {
                list_errors.adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_2,
                    android.R.id.text1,
                    it.errors
                )
            }
        }
    }
}