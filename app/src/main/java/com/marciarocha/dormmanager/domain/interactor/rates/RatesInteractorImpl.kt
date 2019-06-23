package com.marciarocha.dormmanager.domain.interactor.rates

import com.marciarocha.dormmanager.data.persistence.entity.RateEntity
import com.marciarocha.dormmanager.data.repository.rates.RatesRepository
import com.marciarocha.dormmanager.domain.model.RatesMapper
import io.reactivex.Single
import kotlin.math.floor

class RatesInteractorImpl(private val ratesRepository: RatesRepository) : RatesInteractor {
    override fun getExchangeRates(base: String): Single<List<String>> {
        return ratesRepository.getExchangeRates(base)
            .map { response -> response.rates }
            .map { rates -> RatesMapper(rates) }
            .doOnSuccess { ratesMapper -> cacheExchangeRates(ratesMapper.getRateEntities()) }
            .map { ratesMapper -> ratesMapper.getCurrencies() }
    }

    override fun cacheExchangeRates(rates: List<RateEntity>) {
        ratesRepository.cacheExchangeRates(rates)
    }

    override fun convertCurrency(currentPrice: Double, currency: String): Single<Double> {
        return ratesRepository.getExchangeRate(currency)
            .map { rate -> currentPrice * rate }
            .map { convertedPrice -> floor(convertedPrice * 100) / 100 }
    }


}