package com.marciarocha.dormmanager.domain.model

import com.marciarocha.dormmanager.data.persistence.DormEntity

class DormMapper(private val dormEntity: DormEntity) {

    fun create() = Dorm(dormEntity.description, dormEntity.price, dormEntity.availableBeds)
}