package com.marciarocha.dormmanager.domain.interactor.rates

import com.marciarocha.dormmanager.data.persistence.entity.RateEntity
import io.reactivex.Single


interface RatesInteractor {

    fun getExchangeRates(base: String): Single<List<String>>

    fun cacheExchangeRates(rates: List<RateEntity>)

    fun convertCurrency(currentPrice: Double, currency: String): Single<Double>
}