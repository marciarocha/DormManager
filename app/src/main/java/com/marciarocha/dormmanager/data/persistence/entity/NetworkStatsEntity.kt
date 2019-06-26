package com.marciarocha.dormmanager.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "networkstats")
data class NetworkStatsEntity(
    @ColumnInfo val duration: String,
    @ColumnInfo val action: String,
    @ColumnInfo val status: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Int = 0
}