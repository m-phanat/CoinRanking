package com.m.phanat.coinranking.data.models.mapper

import com.m.phanat.coinranking.data.models.CryptoItemJson
import com.m.phanat.coinranking.ui.models.CryptoItemResponse

class CryptoListMapper {

    fun toCryptoListResponse(json: List<CryptoItemJson>?): List<CryptoItemResponse> {
        with(json) {
            return if (this?.isNotEmpty() == true) {
                this.map { toCryptoItemResponse(it) }
            } else {
                emptyList()
            }
        }
    }

    private fun toCryptoItemResponse(json: CryptoItemJson): CryptoItemResponse {
        with(json) {
            return CryptoItemResponse(
                id = id,
                name = name,
                symbol = symbol,
                cmcRank = cmcRank,
                price = quote.usd.price,
                volume24h = quote.usd.volume24h,
                percentChange24h = quote.usd.percentChange24h,
                marketCap = quote.usd.marketCap
            )
        }
    }
}