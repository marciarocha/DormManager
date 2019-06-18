package com.marciarocha.dormmanager.di.component

import android.app.Application
import com.marciarocha.dormmanager.DormApplication
import com.marciarocha.dormmanager.di.module.AppModule
import com.marciarocha.dormmanager.di.scope.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@PerApplication
@Component(modules = [AndroidInjectionModule::class, AppModule::class])
interface AppComponent : AndroidInjector<DormApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun create(app: Application): Builder

        fun build(): AppComponent

    }
}