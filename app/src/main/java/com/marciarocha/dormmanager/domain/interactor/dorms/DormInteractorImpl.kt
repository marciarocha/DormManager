package com.marciarocha.dormmanager.domain.interactor.dorms

import com.marciarocha.dormmanager.data.repository.dorms.DormRepository
import com.marciarocha.dormmanager.domain.model.Dorm
import com.marciarocha.dormmanager.domain.model.DormMapper
import com.marciarocha.dormmanager.domain.state.DatabaseResult
import com.marciarocha.dormmanager.domain.state.PopulateDatabaseResult
import io.reactivex.Single
import javax.inject.Inject

class DormInteractorImpl @Inject constructor(private val dormRepository: DormRepository) :
    DormInteractor {

    override fun populateDatabase(): Single<PopulateDatabaseResult> {
        return getDorms()
            .flatMap {
                if (it is DatabaseResult.NoResults) {
                    dormRepository.initDatabase()
                    Single.just(PopulateDatabaseResult.Success)
                } else {
                    Single.just(PopulateDatabaseResult.DatabaseAlreadyPopulated)
                }
            }
    }

    override fun getDorms(): Single<DatabaseResult> {
        return dormRepository.getDorms()
            .map { dormEntities -> dormEntities.map { dormEntity -> DormMapper(dormEntity).create() } }
            .map { dorms -> mapResult(dorms) }
    }

    private fun mapResult(dorms: List<Dorm>): DatabaseResult {
        return if (dorms.isEmpty()) {
            DatabaseResult.NoResults
        } else {
            DatabaseResult.Success(dorms)
        }
    }
}