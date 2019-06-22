package com.marciarocha.dormmanager.di.module

import androidx.lifecycle.ViewModelProvider
import com.marciarocha.dormmanager.domain.SelectedDormsManager
import com.marciarocha.dormmanager.domain.interactor.dorms.DormInteractor
import com.marciarocha.dormmanager.ui.main.viewmodel.MainViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideSelectedDormsManager(): SelectedDormsManager = SelectedDormsManager()

    @Provides
    fun provideViewModelProviderFactory(
        dormInteractor: DormInteractor,
        selectedDormsManager: SelectedDormsManager
    ): ViewModelProvider.Factory {
        return MainViewModelProviderFactory(
            dormInteractor,
            selectedDormsManager
        )
    }

}