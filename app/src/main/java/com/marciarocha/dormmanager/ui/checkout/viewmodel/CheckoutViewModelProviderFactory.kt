package com.marciarocha.dormmanager.ui.checkout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marciarocha.dormmanager.domain.interactor.rates.RatesInteractor
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class CheckoutViewModelProviderFactory @Inject constructor(
    private val ratesInteractor: RatesInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CheckoutViewModel(ratesInteractor) as T
    }
}