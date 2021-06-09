package com.m.phanat.coinranking.di


import com.m.phanat.coinranking.data.models.mapper.CryptoListMapper
import org.koin.dsl.module

val mappersModule = module {
    single { CryptoListMapper() }
}
