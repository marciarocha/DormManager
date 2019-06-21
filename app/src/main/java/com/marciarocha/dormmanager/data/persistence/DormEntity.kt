package com.marciarocha.dormmanager.data.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dorms")
data class DormEntity(
    @PrimaryKey @ColumnInfo val description: String,
    @ColumnInfo val price: Int,
    @ColumnInfo val availableBeds: Int
)