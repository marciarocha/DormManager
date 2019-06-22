package com.marciarocha.dormmanager.data.repository.dorms

import com.marciarocha.dormmanager.data.persistence.dao.DormDao
import com.marciarocha.dormmanager.data.persistence.entity.DormEntity
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DormRepositoryImpl @Inject constructor(private val dormDao: DormDao) :
    DormRepository {
    override fun getDorms(): Single<List<DormEntity>> {
        return dormDao.getAllDorms().subscribeOn(Schedulers.io())
    }

    override fun updateAvailableBeds(description: String, availableBeds: Int) {
        dormDao.updateAvailableBeds(description, availableBeds)
    }

    override fun initDatabase() {
        dormDao.insertAll(
            listOf(
                DormEntity("6 bed dorm", 1756, 6, "USD"),
                DormEntity("8 bed dorm", 1450, 8, "USD"),
                DormEntity("12 bed dorm", 1201, 12, "USD")
            )
        )
    }

}