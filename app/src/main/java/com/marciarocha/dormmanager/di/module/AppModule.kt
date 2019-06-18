package com.marciarocha.dormmanager.di.module

import android.content.Context
import com.marciarocha.dormmanager.DormApplication
import com.marciarocha.dormmanager.di.qualifier.ApplicationContext
import com.marciarocha.dormmanager.di.scope.PerApplication
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    @PerApplication
    @ApplicationContext
    abstract fun provideApplicationContext(application: DormApplication): Context
}