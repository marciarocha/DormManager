package com.marciarocha.dormmanager.di.component

import com.marciarocha.dormmanager.DormApplication
import com.marciarocha.dormmanager.di.module.*
import com.marciarocha.dormmanager.di.scope.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@PerApplication
@Component(
    modules = [AndroidInjectionModule::class,
        AppModule::class,
        PersistenceModule::class,
        RepositoryModule::class,
        ActivityBindingsModule::class,
        NetworkModule::class,
        InteractorModule::class]
)
interface AppComponent : AndroidInjector<DormApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun create(app: DormApplication): Builder

        fun build(): AppComponent

    }
}