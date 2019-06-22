package com.marciarocha.dormmanager.di.module


import androidx.lifecycle.ViewModelProvider
import com.marciarocha.dormmanager.domain.interactor.rates.RatesInteractor
import com.marciarocha.dormmanager.ui.checkout.CheckoutViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class CheckoutActivityModule {

    @Provides
    fun provideViewModelProviderFactory(ratesInteractor: RatesInteractor): ViewModelProvider.Factory {
        return CheckoutViewModelProviderFactory(ratesInteractor)
    }
}