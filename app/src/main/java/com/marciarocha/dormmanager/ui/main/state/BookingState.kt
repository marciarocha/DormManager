package com.marciarocha.dormmanager.ui.main.state

import android.view.View

data class BookingState(private val totalCost: Int) {

    val price = totalCost / 100.00
    val visibility = isButtonVisible()

    private fun isButtonVisible(): Int {
        return if (totalCost > 0.0) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}