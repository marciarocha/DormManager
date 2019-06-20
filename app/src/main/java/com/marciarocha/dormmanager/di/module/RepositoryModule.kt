package com.marciarocha.dormmanager.di.module

import com.marciarocha.dormmanager.data.persistence.DormDao
import com.marciarocha.dormmanager.data.repository.DormRepository
import com.marciarocha.dormmanager.data.repository.DormRepositoryImpl
import com.marciarocha.dormmanager.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @PerApplication
    fun provideDormRepository(dormDao: DormDao): DormRepository = DormRepositoryImpl(dormDao)

}