package com.marciarocha.dormmanager.domain.interactor.dorms

import com.marciarocha.dormmanager.domain.state.DatabaseResult
import com.marciarocha.dormmanager.domain.state.PopulateDatabaseResult
import io.reactivex.Single

interface DormInteractor {
    fun populateDatabase(): Single<PopulateDatabaseResult>
    fun getDorms(): Single<DatabaseResult>
}