package com.marciarocha.dormmanager.di.module

import android.content.Context
import com.marciarocha.dormmanager.DormApplication
import com.marciarocha.dormmanager.di.qualifier.ApplicationContext
import com.marciarocha.dormmanager.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @PerApplication
    @ApplicationContext
    @Provides
    fun provideApplicationContext(application: DormApplication): Context = application


}