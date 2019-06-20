package com.marciarocha.dormmanager.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marciarocha.dormmanager.domain.state.DatabaseResult
import com.marciarocha.dormmanager.domain.usecase.DormInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(private val dormInteractor: DormInteractor) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val availableDormsState = MutableLiveData<AvailableDormsState>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
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