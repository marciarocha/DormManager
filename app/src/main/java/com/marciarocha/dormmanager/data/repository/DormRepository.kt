package com.marciarocha.dormmanager.data.repository

import com.marciarocha.dormmanager.data.persistence.DormEntity
import io.reactivex.Single

interface DormRepository {

    fun getDorms(): Single<List<DormEntity>>
    fun updateAvailableBeds(description: String, availableBeds: Int)
    fun populateDatabase()
}