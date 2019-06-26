package com.marciarocha.dormmanager.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marciarocha.dormmanager.data.persistence.dao.DormDao
import com.marciarocha.dormmanager.data.persistence.dao.RatesDao
import com.marciarocha.dormmanager.data.persistence.entity.DormEntity
import com.marciarocha.dormmanager.data.persistence.entity.RateEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [DormEntity::class, RateEntity::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dormDao(): DormDao
    abstract fun ratesDao(): RatesDao

}