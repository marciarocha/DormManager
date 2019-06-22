package com.marciarocha.dormmanager.domain.model

import com.marciarocha.dormmanager.data.persistence.entity.RateEntity

data class RatesMapper(private val rates: HashMap<String, Double>) {

    fun getRateEntities(): List<RateEntity> {
        val rateEntities = ArrayList<RateEntity>()
        this.rates.forEach { (currency, rate) ->
            rateEntities.add(RateEntity(currency, rate))
        }
        return rateEntities
    }

    fun getCurrencies(): List<String> {
        return this.rates.keys.toList()
    }
}