package com.marciarocha.dormmanager.domain.interactor.rates

import com.marciarocha.dormmanager.data.persistence.entity.RateEntity
import io.reactivex.Single


interface RatesInteractor {

    fun getCurrencies(base: String): Single<List<String>>

    fun cacheRates(rates: List<RateEntity>)

    fun getRate(currentPrice: Double, currency: String): Single<Double>
}