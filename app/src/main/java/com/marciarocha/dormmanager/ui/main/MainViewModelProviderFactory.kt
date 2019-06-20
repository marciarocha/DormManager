package com.marciarocha.dormmanager.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marciarocha.dormmanager.domain.usecase.DormInteractor
import javax.inject.Inject

class MainViewModelProviderFactory @Inject constructor(
    private val dormInteractor: DormInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(dormInteractor) as T
    }
}