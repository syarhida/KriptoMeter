package com.syarhida.kriptometer.network

import com.syarhida.kriptometer.model.CryptoResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/tickers/")
    suspend fun getCryptoData(): Response<CryptoResponse>
}

