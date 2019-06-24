package com.marciarocha.dormmanager.ui.payment.state

sealed class CurrenciesState {
    object Loading : CurrenciesState()
    data class Loaded(val currencies: List<String>) : CurrenciesState()
    object Error : CurrenciesState()
}