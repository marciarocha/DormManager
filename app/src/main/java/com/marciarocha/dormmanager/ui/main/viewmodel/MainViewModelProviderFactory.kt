package com.marciarocha.dormmanager.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marciarocha.dormmanager.domain.SelectedDormsManager
import com.marciarocha.dormmanager.domain.interactor.dorms.DormInteractor
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainViewModelProviderFactory @Inject constructor(
    private val dormInteractor: DormInteractor,
    private val selectedDormsManager: SelectedDormsManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(dormInteractor, selectedDormsManager) as T
    }
}