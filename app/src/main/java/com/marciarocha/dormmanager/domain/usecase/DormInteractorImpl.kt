package com.marciarocha.dormmanager.domain.usecase

import com.marciarocha.dormmanager.data.repository.DormRepository
import com.marciarocha.dormmanager.domain.model.Dorm
import com.marciarocha.dormmanager.domain.model.DormMapper
import com.marciarocha.dormmanager.domain.state.DormResult
import com.marciarocha.dormmanager.domain.state.PopulateDatabaseResult
import io.reactivex.Single
import javax.inject.Inject

class DormInteractorImpl @Inject constructor(private val dormRepository: DormRepository) : DormInteractor {

    override fun populateDatabase(): Single<PopulateDatabaseResult> {
        return getDorms()
            .flatMap {
                if (it is DormResult.NoResults) {
                    dormRepository.populateDatabase()
                    Single.just(PopulateDatabaseResult.Success)
                } else {
                    Single.just(PopulateDatabaseResult.DatabaseAlreadySeeded)
                }
            }

    }

    override fun getDorms(): Single<DormResult> {
        return dormRepository.getDorms()
            .map { dormEntities -> dormEntities.map { dormEntity -> DormMapper(dormEntity).create() } }
            .map { dorms -> mapResult(dorms) }
    }

    private fun mapResult(dorms: List<Dorm>): DormResult {
        return if (dorms.isEmpty()) {
            DormResult.NoResults
        } else {
            DormResult.Success(dorms)
        }
    }
}