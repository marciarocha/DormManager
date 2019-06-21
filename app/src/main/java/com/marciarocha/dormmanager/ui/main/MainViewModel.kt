package com.marciarocha.dormmanager.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marciarocha.dormmanager.domain.SelectedDormsManager
import com.marciarocha.dormmanager.domain.interactor.DormInteractor
import com.marciarocha.dormmanager.domain.model.Dorm
import com.marciarocha.dormmanager.domain.state.DatabaseResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    private val dormInteractor: DormInteractor,
    private val selectedDormManager: SelectedDormsManager
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val availableDormsState = MutableLiveData<AvailableDormsState>()
    val totalCost = MutableLiveData<ShoppingCartState>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun selectedDorm(dorm: Dorm, result: Int) {
        selectedDormManager.putDorm(dorm, result)
        totalCost.postValue(ShoppingCartState(selectedDormManager.getTotalPrice()))
    }

    fun getDorms() {
        availableDormsState.value = AvailableDormsState.Loading

        compositeDisposable.add(
            dormInteractor.getDorms()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ dormResult ->
                    when (dormResult) {
                        is DatabaseResult.Success -> {
                            availableDormsState.value = AvailableDormsState.Loaded(dormResult.dorms)
                        }
                        is DatabaseResult.NoResults -> {
                            availableDormsState.value = AvailableDormsState.Error
                        }
                    }
                },
                    {
                        availableDormsState.value = AvailableDormsState.Error
                        Log.e("getDorms", it.message)
                    })
        )
    }

}