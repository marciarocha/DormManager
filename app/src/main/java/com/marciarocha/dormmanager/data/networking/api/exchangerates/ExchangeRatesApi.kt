package com.marciarocha.dormmanager.data.networking.api.exchangerates

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRatesApi {

    @GET("/latest")
    fun getLatestRates(@Query("base") base: String): Single<RatesResponse>
}