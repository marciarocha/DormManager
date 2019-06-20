package com.marciarocha.dormmanager.ui.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marciarocha.dormmanager.domain.state.PopulateDatabaseResult
import com.marciarocha.dormmanager.domain.usecase.DormInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel(
    private val dormInteractor: DormInteractor
) : ViewModel() {

    val databaseState = MutableLiveData<DatabaseState>()

    private val compositeDisposable = CompositeDisposable()

    fun populateDatabaseIfEmpty() {
        databaseState.value = DatabaseState.Loading

        compositeDisposable.add(
            dormInteractor.populateDatabase().observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        when (it) {
                            is PopulateDatabaseResult.Success -> {
                                databaseState.value = DatabaseState.DatabaseLoaded
                            }
                            is PopulateDatabaseResult.DatabaseAlreadySeeded -> {
                                DatabaseState.DatabaseLoaded
                            }
                        }
                    },
                    {
                        databaseState.value = DatabaseState.Error
                        Log.e("getDorms", it.message)
                    })
        )
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}