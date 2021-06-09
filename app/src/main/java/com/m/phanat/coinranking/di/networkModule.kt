package com.m.phanat.coinranking.di

import com.m.phanat.coinranking.data.providers.RetrofitProvider
import org.koin.dsl.module

private const val BASE_URL = "https://pro-api.coinmarketcap.com"

val networkModule = module {
    single {
        RetrofitProvider(get(), BASE_URL).provide()
    }
}