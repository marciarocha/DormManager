package com.marciarocha.dormmanager.di.module

import com.marciarocha.dormmanager.data.repository.dorms.DormRepository
import com.marciarocha.dormmanager.data.repository.networkStats.NetworkStatsRepository
import com.marciarocha.dormmanager.data.repository.rates.RatesRepository
import com.marciarocha.dormmanager.di.scope.PerApplication
import com.marciarocha.dormmanager.domain.interactor.dorms.DormInteractor
import com.marciarocha.dormmanager.domain.interactor.dorms.DormInteractorImpl
import com.marciarocha.dormmanager.domain.interactor.networkstats.NetworkStatsInteractor
import com.marciarocha.dormmanager.domain.interactor.networkstats.NetworkStatsInteractorImpl
import com.marciarocha.dormmanager.domain.interactor.rates.RatesInteractor
import com.marciarocha.dormmanager.domain.interactor.rates.RatesInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    @PerApplication
    fun provideDormInteractor(dormRepository: DormRepository): DormInteractor {
        return DormInteractorImpl(dormRepository)
    }

    @Provides
    @PerApplication
    fun provideRatesInteractor(ratesRepository: RatesRepository): RatesInteractor {
        return RatesInteractorImpl(ratesRepository)
    }

    @Provides
    @PerApplication
    fun provideNetworkStatsInteractor(networkStatsRepository: NetworkStatsRepository): NetworkStatsInteractor {
        return NetworkStatsInteractorImpl(networkStatsRepository)
    }
}