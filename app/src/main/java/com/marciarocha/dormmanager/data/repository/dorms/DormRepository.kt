package com.marciarocha.dormmanager.data.repository.dorms

import com.marciarocha.dormmanager.data.persistence.entity.DormEntity
import io.reactivex.Single

interface DormRepository {

    fun getDorms(): Single<List<DormEntity>>
    fun updateAvailableBeds(description: String, availableBeds: Int)
    fun populateDatabase()
}