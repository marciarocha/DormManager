package com.marciarocha.dormmanager.di.module

import android.content.Context
import androidx.room.Room
import com.marciarocha.dormmanager.data.persistence.AppDatabase
import com.marciarocha.dormmanager.data.persistence.dao.DormDao
import com.marciarocha.dormmanager.data.persistence.dao.NetworkStatsDao
import com.marciarocha.dormmanager.data.persistence.dao.RatesDao
import com.marciarocha.dormmanager.di.qualifier.ApplicationContext
import com.marciarocha.dormmanager.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class PersistenceModule {

    @Provides
    @PerApplication
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "DormManager.db")
            .build()
    }

    @Provides
    @PerApplication
    internal fun provideDormDao(appDatabase: AppDatabase): DormDao = appDatabase.dormDao()

    @Provides
    @PerApplication
    internal fun provideRatesDao(appDatabase: AppDatabase): RatesDao = appDatabase.ratesDao()

    @Provides
    @PerApplication
    internal fun provideNetworkStatsDao(appDatabase: AppDatabase): NetworkStatsDao = appDatabase.networkStatsDao()
}