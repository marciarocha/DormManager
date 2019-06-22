package com.marciarocha.dormmanager.data.repository.rates

import com.marciarocha.dormmanager.data.networking.RatesResponse
import com.marciarocha.dormmanager.data.persistence.entity.RateEntity
import io.reactivex.Single

interface RatesRepository {

    fun getRates(base: String): Single<RatesResponse>

    fun cacheRates(rates: List<RateEntity>)

    fun getRate(currency: String): Single<Double>
}