package com.marciarocha.dormmanager.di.module

import androidx.lifecycle.ViewModelProvider
import com.marciarocha.dormmanager.data.repository.DormRepository
import com.marciarocha.dormmanager.domain.usecase.DormInteractor
import com.marciarocha.dormmanager.domain.usecase.DormInteractorImpl
import com.marciarocha.dormmanager.ui.main.MainViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideInteractor(dormRepository: DormRepository): DormInteractor {
        return DormInteractorImpl(dormRepository)
    }

    @Provides
    fun provideViewModelProviderFactory(dormInteractor: DormInteractor): ViewModelProvider.Factory {
        return MainViewModelProviderFactory(dormInteractor)
    }

}