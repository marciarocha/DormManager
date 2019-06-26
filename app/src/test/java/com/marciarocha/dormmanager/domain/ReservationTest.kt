package com.marciarocha.dormmanager.domain

import com.marciarocha.dormmanager.domain.model.Dorm
import com.marciarocha.dormmanager.domain.model.Reservation
import org.junit.Assert.assertEquals
import org.junit.Test

class ReservationTest {

    @Test
    fun getTotalPrice_noDormsSelected_returnsTrue() {
        val reservation = Reservation()
        val price = reservation.getTotalPrice()
        assertEquals(0, price)
    }

    @Test
    fun getTotalPrice_hasDormsSelected_returnsTrue() {
        val reservation = Reservation()
        val dorm = Dorm("description", 12, 10, "USD")

        reservation.addDorm(dorm, 1)
        val price = reservation.getTotalPrice()
        assertEquals(12, price)
    }

    @Test
    fun getTotalPrice_removeSelectedBedsFromDorm_returnsTrue() {
        val reservation = Reservation()
        val dorm = Dorm("description", 12, 10, "USD")
        reservation.addDorm(dorm, 1)
        reservation.addDorm(dorm, 0)

        val price = reservation.getTotalPrice()
        assertEquals(0, price)
    }


}