package com.marciarocha.dormmanager.di.module

import androidx.lifecycle.ViewModelProvider
import com.marciarocha.dormmanager.domain.interactor.dorms.DormInteractor
import com.marciarocha.dormmanager.viewmodel.splash.SplashViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    @Provides
    fun provideViewModelProviderFactory(dormInteractor: DormInteractor): ViewModelProvider.Factory {
        return SplashViewModelProviderFactory(dormInteractor)
    }

}