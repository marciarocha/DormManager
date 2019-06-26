package com.marciarocha.dormmanager.domain

import com.marciarocha.dormmanager.domain.model.Dorm
import com.marciarocha.dormmanager.domain.model.Reservation
import org.junit.Assert.assertEquals
import org.junit.Test

class ReservationTest {

    @Test
    fun getTotalPrice_noDormsSelected_returnsTrue() {
        val selectedDormsManager = Reservation()
        val price = selectedDormsManager.getTotalPrice()
        assertEquals(0, price)
    }

    @Test
    fun getTotalPrice_hasDormsSelected_returnsTrue() {
        val selectedDormsManager = Reservation()
        val dorm = Dorm("description", 12, 10, "USD")

        selectedDormsManager.addDorm(dorm, 1)
        val price = selectedDormsManager.getTotalPrice()
        assertEquals(12, price)
    }
}