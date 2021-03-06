package com.m.phanat.coinranking.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorJson(
    val status: ApiExceptionBody
)