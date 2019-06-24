package com.marciarocha.dormmanager.di.module


import androidx.lifecycle.ViewModelProvider
import com.marciarocha.dormmanager.domain.interactor.rates.RatesInteractor
import com.marciarocha.dormmanager.ui.payment.viewmodel.PaymentViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class PaymentActivityModule {

    @Provides
    fun provideViewModelProviderFactory(ratesInteractor: RatesInteractor): ViewModelProvider.Factory {
        return PaymentViewModelProviderFactory(ratesInteractor)
    }
}