package com.marciarocha.dormmanager.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface DormDao {
    @Query("SELECT * from dorms")
    fun getAllDorms(): Single<List<DormEntity>>

    @Query("UPDATE dorms SET availableBeds = :availableBeds WHERE description = :description")
    fun updateAvailableBeds(description: String, availableBeds: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(
        dorms: List<DormEntity> = listOf(
            DormEntity("6 bed dorm", 17.56, 6),
            DormEntity("8 bed dorm", 14.50, 8),
            DormEntity("12 bed dorm", 12.01, 12)
        )
    )
}