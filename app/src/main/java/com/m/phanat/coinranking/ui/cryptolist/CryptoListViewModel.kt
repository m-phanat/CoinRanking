package com.m.phanat.coinranking.ui.cryptolist

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.m.phanat.coinranking.data.repository.CryptoListRepository
import com.m.phanat.coinranking.ui.base.BaseViewModel
import com.m.phanat.coinranking.ui.models.CryptoItemResponse
import kotlinx.coroutines.flow.Flow

class CryptoListViewModel(private val repository: CryptoListRepository) : BaseViewModel() {

    private var _cryptoList: Flow<PagingData<CryptoItemResponse>>? = null

    fun getCryptoList(): Flow<PagingData<CryptoItemResponse>> {
        val response = repository.getCryptoList().cachedIn(viewModelScope)
        _cryptoList = response
        return response
    }
}