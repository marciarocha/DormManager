package com.marciarocha.dormmanager.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marciarocha.dormmanager.data.persistence.entity.RateEntity
import io.reactivex.Single

@Dao
interface RatesDao {
    @Query("SELECT rate FROM rates WHERE currency = :currency")
    fun getRate(currency: String): Single<Double>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(rates: List<RateEntity>)
}