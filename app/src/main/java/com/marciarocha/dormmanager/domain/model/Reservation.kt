package com.marciarocha.dormmanager.domain.model

class Reservation {
    private val dorms = mutableListOf<Dorm>()

    fun addDorm(dorm: Dorm, selectedBeds: Int) {
        if (dorms.contains(dorm)) {
            val position = dorms.indexOf(dorm)
            dorms[position].bookedBeds = selectedBeds
        } else {
            dorm.bookedBeds = selectedBeds
            dorms.add(dorm)
        }
    }

    fun getTotalPrice(): Int {
        var totalPrice = 0
        dorms.forEach { dorm: Dorm -> totalPrice += dorm.getPriceForReservation() }
        return totalPrice
    }

    fun clear() {
        dorms.clear()
    }

}