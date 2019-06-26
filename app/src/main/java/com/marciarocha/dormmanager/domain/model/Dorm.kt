package com.marciarocha.dormmanager.domain.model

import java.io.Serializable

data class Dorm(
    val description: String,
    val price: Int,
    val availableBeds: Int,
    val currency: String
) : Serializable {

    var bookedBeds: Int = 0

    fun getFormattedPrice(): Double = price / 100.00

    fun getPriceForReservation(): Int = this.bookedBeds * this.price

    fun getListOfBeds(): List<Int> {
        val listOfBeds = mutableListOf<Int>()

        for (i in 0..this.availableBeds) {
            listOfBeds.add(i)
        }
        return listOfBeds
    }


}