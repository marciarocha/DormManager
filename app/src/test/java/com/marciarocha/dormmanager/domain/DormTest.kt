package com.marciarocha.dormmanager.domain

import com.marciarocha.dormmanager.domain.model.Dorm
import junit.framework.Assert.assertEquals
import org.junit.Test

class DormTest {

    @Test
    fun getFormattedPrice_returnsTrue() {
        val dorm = Dorm("", 123, 1, "")
        assertEquals(1.23, dorm.getFormattedPrice(), 0.0)
    }

    @Test
    fun getPriceForReservation_returnsTrue() {
        val dorm = Dorm("", 100, 2, "")
        dorm.bookedBeds = 2
        assertEquals(200, dorm.getPriceForReservation())
    }

    @Test
    fun getListOfBeds_returnsTrue() {
        val dorm = Dorm("", 100, 5, "")
        val expectedList = listOf(0, 1, 2, 3, 4, 5)
        assertEquals(expectedList, dorm.getListOfBeds())
    }
}