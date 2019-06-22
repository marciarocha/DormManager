package com.marciarocha.dormmanager.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates")
class RateEntity(
    @PrimaryKey @ColumnInfo val currency: String,
    @ColumnInfo val rate: Double
)