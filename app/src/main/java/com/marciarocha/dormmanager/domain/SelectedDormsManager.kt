package com.marciarocha.dormmanager.domain

import com.marciarocha.dormmanager.domain.model.Dorm

class SelectedDormsManager {

    private val map = HashMap<Dorm, Int>()

    fun putDorm(dorm: Dorm, selectedBeds: Int) {
        map[dorm] = selectedBeds
    }

    fun isValidShoppingCart(): Boolean {
        if (this.getTotalPrice() > 0) {
            return true
        }
        return false
    }

    fun getTotalPrice(): Double {
        var totalPrice: Double = 0.0
        map.forEach { (t, u) -> totalPrice += (t.price * u) }

        return totalPrice
    }

}