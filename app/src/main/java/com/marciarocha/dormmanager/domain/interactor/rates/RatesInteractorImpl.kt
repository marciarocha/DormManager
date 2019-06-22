package com.marciarocha.dormmanager.domain.interactor.rates

import com.marciarocha.dormmanager.data.persistence.entity.RateEntity
import com.marciarocha.dormmanager.data.repository.rates.RatesRepository
import com.marciarocha.dormmanager.domain.model.RatesMapper
import io.reactivex.Single
import kotlin.math.floor

class RatesInteractorImpl(private val ratesRepository: RatesRepository) : RatesInteractor {
    override fun getCurrencies(base: String): Single<List<String>> {
        return ratesRepository.getRates(base)
            .map { response -> response.rates }
            .map { rates -> RatesMapper(rates) }
            .doOnSuccess { ratesMapper -> cacheRates(ratesMapper.getRateEntities()) }
            .map { ratesMapper -> ratesMapper.getCurrencies() }
    }

    override fun cacheRates(rates: List<RateEntity>) {
        ratesRepository.cacheRates(rates)
    }

    override fun getRate(currentPrice: Double, currency: String): Single<Double> {
        return ratesRepository.getRate(currency)
            .map { rate -> currentPrice * rate }
            .map { convertedPrice -> floor(convertedPrice * 100) / 100 }
    }


}