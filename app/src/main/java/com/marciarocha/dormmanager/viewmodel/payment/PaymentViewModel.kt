package com.marciarocha.dormmanager.viewmodel.payment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marciarocha.dormmanager.domain.interactor.rates.RatesInteractor
import com.marciarocha.dormmanager.ui.payment.state.ConversionState
import com.marciarocha.dormmanager.ui.payment.state.CurrenciesState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class PaymentViewModel(private val ratesInteractor: RatesInteractor) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _ratesState = MutableLiveData<CurrenciesState>()
    val currenciesState: LiveData<CurrenciesState> = _ratesState

    private val _conversionState = MutableLiveData<ConversionState>()
    val conversionState: LiveData<ConversionState> = _conversionState

    init {
        getRates("USD")
    }

    private fun getRates(base: String) {
        _ratesState.value = CurrenciesState.Loading

        compositeDisposable.add(
            ratesInteractor.getExchangeRates(base)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ rates -> _ratesState.value = CurrenciesState.Loaded(rates) },
                    { error ->
                        _ratesState.value = CurrenciesState.Error
                        Log.e("getExchangeRates,base=$base", error.message)
                    })
        )
    }

    fun onSelectCurrency(currentPrice: Double, currency: String) {
        _conversionState.value = ConversionState.Loading

        compositeDisposable.add(
            ratesInteractor.convertCurrency(currentPrice, currency).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ convertedPrice -> _conversionState.value = ConversionState.Loaded(convertedPrice) },
                    { error ->
                        _conversionState.value = ConversionState.Error
                        Log.e("convertCurrency($currency,$currentPrice=", error.message)
                    })
        )
    }

}