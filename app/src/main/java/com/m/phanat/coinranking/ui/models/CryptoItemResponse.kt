package com.m.phanat.coinranking.ui.models

import com.m.phanat.coinranking.extentions.toDecimals
import com.m.phanat.coinranking.extentions.toDecimalsNoComma
import com.m.phanat.coinranking.extentions.toDecimalsUnits

data class CryptoItemResponse(
    val id: Int,
    val name: String,
    val symbol: String,
    val cmcRank: String,
    val price: Double,
    val volume24h: Double,
    val percentChange24h: Double,
    val marketCap: Double
) {
    val priceDisplay: String
        get() = price.toDecimals()

    val priceDisplayNoComma: String
        get() = price.toDecimalsNoComma()

    val percentChangeDisplay: String
        get() = percentChange24h.toDecimals()

    val volumeDisplay: String
        get() = volume24h.toDecimalsUnits()
}