package com.marciarocha.dormmanager.data.repository

import com.marciarocha.dormmanager.data.persistence.DormDao
import com.marciarocha.dormmanager.data.persistence.DormEntity
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DormRepositoryImpl @Inject constructor(private val dormDao: DormDao) : DormRepository {
    override fun getDorms(): Single<List<DormEntity>> {
        return dormDao.getAllDorms().subscribeOn(Schedulers.io())
    }

    override fun updateAvailableBeds(description: String, availableBeds: Int) {
        dormDao.updateAvailableBeds(description, availableBeds)
    }

    override fun populateDatabase() {
        dormDao.insertAll()
    }

}