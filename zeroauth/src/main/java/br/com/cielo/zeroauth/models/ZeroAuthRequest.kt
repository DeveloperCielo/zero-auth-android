package br.com.cielo.zeroauth.models

import com.google.gson.annotations.SerializedName

data class ZeroAuthRequest (
    @SerializedName("CardType")
    val cardType: CardType? = null,

    @SerializedName("CardNumber")
    val cardNumber: String? = null,

    @SerializedName("Holder")
    val holder: String? = null,

    @SerializedName("ExpirationDate")
    val expirationDate: String? = null,

    @SerializedName("SecurityCode")
    val securityCode: String? = null,

    @SerializedName("SaveCard")
    val saveCard: Boolean,

    @SerializedName("Brand")
    val brand: String,

    @SerializedName("CardOnFile")
    val cardOnFile: CardOnFile? = null,

    @SerializedName("CardToken")
    val cardToken: String? = null
)

enum class CardType {
    @SerializedName("CreditCard")
    CREDIT_CARD,

    @SerializedName("DebitCard")
    DEBIT_CARD
}

data class CardOnFile (
    @SerializedName("Usage")
    val usage: Usage? = null,

    @SerializedName("Reason")
    val reason: Reason? = null
)

enum class Usage {
    @SerializedName("First")
    FIRST,

    @SerializedName("Used")
    USED
}

enum class Reason {
    @SerializedName("Recurring")
    RECURRING,

    @SerializedName("Unscheduled")
    UNSCHEDULED,

    @SerializedName("Installments")
    INSTALLMENTS
}