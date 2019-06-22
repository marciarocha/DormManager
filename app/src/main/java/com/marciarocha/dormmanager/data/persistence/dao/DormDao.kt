package com.marciarocha.dormmanager.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marciarocha.dormmanager.data.persistence.entity.DormEntity
import io.reactivex.Single

@Dao
interface DormDao {
    @Query("SELECT * from dorms")
    fun getAllDorms(): Single<List<DormEntity>>

    @Query("UPDATE dorms SET availableBeds = :availableBeds WHERE description = :description")
    fun updateAvailableBeds(description: String, availableBeds: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(dormEntities: List<DormEntity>)

}