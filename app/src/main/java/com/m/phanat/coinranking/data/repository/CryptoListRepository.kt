package com.m.phanat.coinranking.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.m.phanat.coinranking.data.api.APIService
import com.m.phanat.coinranking.data.models.mapper.CryptoListMapper
import com.m.phanat.coinranking.data.pagingsources.CryptoListPagingSource
import com.m.phanat.coinranking.data.pagingsources.NETWORK_PAGE_SIZE
import com.m.phanat.coinranking.ui.models.CryptoItemResponse

interface CryptoListRepository {
    suspend fun getCryptoList(): LiveData<PagingData<CryptoItemResponse>>
}

class CryptoRepositoryImpl(
    private val service: APIService,
    private val mapper: CryptoListMapper
) : CryptoListRepository {
    override suspend fun getCryptoList(): LiveData<PagingData<CryptoItemResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CryptoListPagingSource(service, mapper)
            }
        ).liveData
    }
}