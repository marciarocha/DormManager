package com.marciarocha.dormmanager.data.repository.rates

import com.marciarocha.dormmanager.data.networking.ExchangeRatesApi
import com.marciarocha.dormmanager.data.networking.RatesResponse
import com.marciarocha.dormmanager.data.persistence.dao.RatesDao
import com.marciarocha.dormmanager.data.persistence.entity.RateEntity
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    private val exchangeRatesApi: ExchangeRatesApi,
    private val ratesDao: RatesDao
) : RatesRepository {

    override fun getExchangeRates(base: String): Single<RatesResponse> {
        return exchangeRatesApi.getLatestRates(base)
            .subscribeOn(Schedulers.io())
    }

    override fun cacheExchangeRates(rates: List<RateEntity>) {
        ratesDao.insertAll(rates)
    }

    override fun getExchangeRate(currency: String): Single<Double> {
        return ratesDao.getRate(currency).subscribeOn(Schedulers.io())
    }
}