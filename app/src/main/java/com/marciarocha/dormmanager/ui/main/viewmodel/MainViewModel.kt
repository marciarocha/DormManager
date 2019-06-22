package com.marciarocha.dormmanager.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marciarocha.dormmanager.domain.SelectedDormsManager
import com.marciarocha.dormmanager.domain.interactor.dorms.DormInteractor
import com.marciarocha.dormmanager.domain.model.Dorm
import com.marciarocha.dormmanager.domain.state.DatabaseResult
import com.marciarocha.dormmanager.ui.main.state.AvailableDormsState
import com.marciarocha.dormmanager.ui.main.state.ShoppingCartState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    private val dormInteractor: DormInteractor,
    private val selectedDormManager: SelectedDormsManager
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _availableDormsState = MutableLiveData<AvailableDormsState>()
    val availableDormsState: LiveData<AvailableDormsState> = _availableDormsState

    private val _totalCost = MutableLiveData<ShoppingCartState>()
    val totalCost: LiveData<ShoppingCartState> = _totalCost

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun onDormSelected(dorm: Dorm, result: Int) {
        selectedDormManager.putDorm(dorm, result)
        postTotalPrice()
    }

    fun getDorms() {
        _availableDormsState.value = AvailableDormsState.Loading

        compositeDisposable.add(
            dormInteractor.getDorms()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ dormResult ->
                    when (dormResult) {
                        is DatabaseResult.Success -> {
                            _availableDormsState.value = AvailableDormsState.Loaded(dormResult.dorms)
                        }
                        is DatabaseResult.NoResults -> {
                            _availableDormsState.value = AvailableDormsState.Error
                        }
                    }
                },
                    {
                        _availableDormsState.value = AvailableDormsState.Error
                        Log.e("getDorms", it.message)
                    })
        )
    }

    fun clearSelectedDorms() {
        selectedDormManager.clearDorms()
        postTotalPrice()
    }

    private fun postTotalPrice() {
        _totalCost.value = ShoppingCartState(selectedDormManager.getTotalPrice())
    }

}