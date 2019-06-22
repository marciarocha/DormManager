package com.marciarocha.dormmanager.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marciarocha.dormmanager.domain.interactor.dorms.DormInteractor
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class SplashViewModelProviderFactory @Inject constructor(
    private val dormInteractor: DormInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel(dormInteractor) as T
    }

}