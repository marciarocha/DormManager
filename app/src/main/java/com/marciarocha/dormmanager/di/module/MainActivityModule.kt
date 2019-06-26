package com.marciarocha.dormmanager.di.module

import androidx.lifecycle.ViewModelProvider
import com.marciarocha.dormmanager.domain.interactor.dorms.DormInteractor
import com.marciarocha.dormmanager.domain.model.Reservation
import com.marciarocha.dormmanager.viewmodel.main.MainViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideReservation(): Reservation =
        Reservation()

    @Provides
    fun provideViewModelProviderFactory(
        dormInteractor: DormInteractor,
        reservation: Reservation
    ): ViewModelProvider.Factory {
        return MainViewModelProviderFactory(
            dormInteractor,
            reservation
        )
    }

}