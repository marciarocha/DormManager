package com.marciarocha.dormmanager.viewmodel.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marciarocha.dormmanager.domain.interactor.dorms.DormInteractor
import com.marciarocha.dormmanager.domain.state.PopulateDatabaseResult
import com.marciarocha.dormmanager.ui.splash.state.DatabaseState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel(
    private val dormInteractor: DormInteractor
) : ViewModel() {

    private val _databaseState = MutableLiveData<DatabaseState>()
    val databaseState: LiveData<DatabaseState> = _databaseState

    private val compositeDisposable = CompositeDisposable()

    init {
        populateDatabaseIfEmpty()
    }

    private fun populateDatabaseIfEmpty() {
        _databaseState.value = DatabaseState.Loading

        compositeDisposable.add(
            dormInteractor.populateDatabase().observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        when (it) {
                            is PopulateDatabaseResult.Success -> {
                                _databaseState.value = DatabaseState.DatabaseLoaded
                            }
                            is PopulateDatabaseResult.DatabaseAlreadyPopulated -> {
                                _databaseState.value = DatabaseState.DatabaseLoaded
                            }
                        }
                    },
                    {
                        _databaseState.value = DatabaseState.Error
                        Log.e("getDorms()", it.message)
                    })
        )
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}