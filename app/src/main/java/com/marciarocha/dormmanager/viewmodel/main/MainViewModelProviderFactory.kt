package com.marciarocha.dormmanager.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marciarocha.dormmanager.domain.interactor.dorms.DormInteractor
import com.marciarocha.dormmanager.domain.model.Reservation
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainViewModelProviderFactory @Inject constructor(
    private val dormInteractor: DormInteractor,
    private val reservation: Reservation
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(dormInteractor, reservation) as T
    }
}