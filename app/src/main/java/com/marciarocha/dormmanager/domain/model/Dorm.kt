package com.marciarocha.dormmanager.domain.model

import java.io.Serializable

data class Dorm(
    val description: String,
    val price: Int,
    val availableBeds: Int
) : Serializable {

    fun getFormattedPrice(): Double {
        return price / 100.00
    }
}