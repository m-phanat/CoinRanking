package com.m.phanat.coinranking.di

import com.m.phanat.coinranking.ui.cryptolist.CryptoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { CryptoListViewModel(get()) }
}
