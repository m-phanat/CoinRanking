package com.m.phanat.coinranking.di

import com.m.phanat.coinranking.data.repository.CryptoListRepository
import com.m.phanat.coinranking.data.repository.CryptoRepositoryImpl
import org.koin.dsl.module

val repoModule = module {
    single<CryptoListRepository> { CryptoRepositoryImpl(get(), get()) }
}
