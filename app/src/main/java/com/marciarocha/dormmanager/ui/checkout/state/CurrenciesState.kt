package com.marciarocha.dormmanager.ui.checkout.state

sealed class CurrenciesState {
    object Loading : CurrenciesState()
    data class Loaded(val currencies: List<String>) : CurrenciesState()
    object Error : CurrenciesState()
}