package com.marciarocha.dormmanager.viewmodel.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marciarocha.dormmanager.domain.interactor.rates.RatesInteractor
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class PaymentViewModelProviderFactory @Inject constructor(
    private val ratesInteractor: RatesInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PaymentViewModel(ratesInteractor) as T
    }
}