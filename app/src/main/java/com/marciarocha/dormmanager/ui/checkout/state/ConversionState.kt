package com.marciarocha.dormmanager.ui.checkout.state

sealed class ConversionState {
    object Loading : ConversionState()
    data class Loaded(val convertedPrice: Double) : ConversionState()
    object Error : ConversionState()
}