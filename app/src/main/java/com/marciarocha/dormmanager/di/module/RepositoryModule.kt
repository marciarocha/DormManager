package com.marciarocha.dormmanager.di.module

import com.marciarocha.dormmanager.data.networking.api.exchangerates.ExchangeRatesApi
import com.marciarocha.dormmanager.data.networking.api.networkstats.NetworkStatsApi
import com.marciarocha.dormmanager.data.persistence.dao.DormDao
import com.marciarocha.dormmanager.data.persistence.dao.RatesDao
import com.marciarocha.dormmanager.data.repository.dorms.DormRepository
import com.marciarocha.dormmanager.data.repository.dorms.DormRepositoryImpl
import com.marciarocha.dormmanager.data.repository.networkStats.NetworkStatsRepository
import com.marciarocha.dormmanager.data.repository.networkStats.NetworkStatsRepositoryImpl
import com.marciarocha.dormmanager.data.repository.rates.RatesRepository
import com.marciarocha.dormmanager.data.repository.rates.RatesRepositoryImpl
import com.marciarocha.dormmanager.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @PerApplication
    fun provideDormRepository(dormDao: DormDao): DormRepository =
        DormRepositoryImpl(dormDao)

    @Provides
    @PerApplication
    fun provideRatesRepository(exchangeRatesApi: ExchangeRatesApi, ratesDao: RatesDao): RatesRepository =
        RatesRepositoryImpl(exchangeRatesApi, ratesDao)

    @Provides
    @PerApplication
    fun provideNetworkStatsRepository(
        networkStartApi: NetworkStatsApi
    ): NetworkStatsRepository =
        NetworkStatsRepositoryImpl(networkStartApi)

}