package com.m.phanat.coinranking.di

import com.m.phanat.coinranking.data.api.APIService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiServicesModule = module {
    single<APIService> {
        get<Retrofit>().create(APIService::class.java)
    }
}
