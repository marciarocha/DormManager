package com.marciarocha.dormmanager.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    exportSchema = false,
    entities = [DormEntity::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dormDao(): DormDao

}