package com.marciarocha.dormmanager.data.repository.rates

import com.marciarocha.dormmanager.data.networking.api.RatesResponse
import com.marciarocha.dormmanager.data.persistence.entity.RateEntity
import io.reactivex.Single

interface RatesRepository {

    fun getExchangeRates(base: String): Single<RatesResponse>

    fun cacheExchangeRates(rates: List<RateEntity>)

    fun getExchangeRate(currency: String): Single<Double>
}