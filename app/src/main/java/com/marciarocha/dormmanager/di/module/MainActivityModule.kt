package com.marciarocha.dormmanager.di.module

import androidx.lifecycle.ViewModelProvider
import com.marciarocha.dormmanager.data.repository.DormRepository
import com.marciarocha.dormmanager.domain.SelectedDormsManager
import com.marciarocha.dormmanager.domain.interactor.DormInteractor
import com.marciarocha.dormmanager.domain.interactor.DormInteractorImpl
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
    fun provideSelectedDormsManager(): SelectedDormsManager = SelectedDormsManager()

    @Provides
    fun provideViewModelProviderFactory(
        dormInteractor: DormInteractor,
        selectedDormsManager: SelectedDormsManager
    ): ViewModelProvider.Factory {
        return MainViewModelProviderFactory(dormInteractor, selectedDormsManager)
    }

}