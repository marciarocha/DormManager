package com.marciarocha.dormmanager.domain

import com.marciarocha.dormmanager.domain.model.Dorm

class SelectedDormsManager {

    private val map = HashMap<Dorm, Int>()

    fun putDorm(dorm: Dorm, selectedBeds: Int) {
        map[dorm] = selectedBeds
    }

    fun getTotalPrice(): Int {
        var totalPrice: Int = 0
        map.forEach { (t, u) -> totalPrice += (t.price * u) }

        return totalPrice
    }

    fun clearDorms() {
        map.clear()
    }

}