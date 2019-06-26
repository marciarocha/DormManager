package com.marciarocha.dormmanager.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marciarocha.dormmanager.data.persistence.entity.NetworkStatsEntity
import io.reactivex.Single


@Dao
interface NetworkStatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNetworkStat(networkStats: NetworkStatsEntity)

    @Query("SELECT * FROM networkstats")
    fun getNetworkStats(): Single<List<NetworkStatsEntity>>

    @Query("DELETE FROM networkstats")
    fun clearNetworkStats()
}