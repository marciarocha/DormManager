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

    override fun getRates(base: String): Single<RatesResponse> {
        return exchangeRatesApi.getLatestRates(base)
            .subscribeOn(Schedulers.io())
    }

    override fun cacheRates(rates: List<RateEntity>) {
        ratesDao.insertAll(rates)
    }

    override fun getRate(currency: String): Single<Double> {
        return ratesDao.getRate(currency).subscribeOn(Schedulers.io())
    }
}