package com.m.phanat.coinranking

import android.app.Application
import com.m.phanat.coinranking.di.apiServicesModule
import com.m.phanat.coinranking.di.viewModelsModule
import com.m.phanat.coinranking.di.appModule
import com.m.phanat.coinranking.di.mappersModule
import com.m.phanat.coinranking.di.networkModule
import com.m.phanat.coinranking.di.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoinRankingApp : Application(){
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@CoinRankingApp)
            modules(appModule, networkModule, repoModule, apiServicesModule, viewModelsModule, mappersModule)
        }
    }
}