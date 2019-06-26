package com.marciarocha.dormmanager.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dorms")
data class DormEntity(
    @ColumnInfo val description: String,
    @ColumnInfo val price: Int,
    @ColumnInfo val availableBeds: Int,
    @ColumnInfo val currency: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Int = 0
}