package com.marciarocha.dormmanager.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marciarocha.dormmanager.data.persistence.entity.DormEntity
import com.marciarocha.dormmanager.data.repository.dorms.DormRepository
import com.marciarocha.dormmanager.domain.interactor.dorms.DormInteractorImpl
import com.marciarocha.dormmanager.domain.model.Dorm
import com.marciarocha.dormmanager.domain.state.DatabaseResult
import com.marciarocha.dormmanager.domain.state.PopulateDatabaseResult
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class DormInteractorTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val dormRepository = mock(DormRepository::class.java)
    private val dormInteractor by lazy { DormInteractorImpl(dormRepository) }

    @Test
    fun getDorms_Completed() {
        `when`(dormRepository.getDorms()).thenReturn(Single.just(emptyList()))
        dormInteractor.getDorms().test().assertComplete()
    }

    @Test
    fun getDorms_Error() {
        val throwable = Throwable("Error response")
        `when`(dormRepository.getDorms()).thenReturn(Single.error(throwable))
        dormInteractor.getDorms().test().assertError(throwable)
    }

    @Test
    fun getDorms_Response_NoResults() {
        `when`(dormRepository.getDorms()).thenReturn(Single.just(emptyList()))

        val response = DatabaseResult.NoResults
        dormInteractor.getDorms().test()
            .assertValue(response)
    }

    @Test
    fun getDorms_Response_Results() {
        val dorms = listOf(DormEntity("", 1, 1, ""))
        `when`(dormRepository.getDorms()).thenReturn(Single.just(dorms))

        val response = DatabaseResult.Success(listOf(Dorm("", 1, 1, "")))
        dormInteractor.getDorms().test()
            .assertValue(response)
    }

    @Test
    fun populateDatabase_Completed() {
        `when`(dormRepository.getDorms()).thenReturn(Single.just(emptyList()))
        dormInteractor.populateDatabase().test().assertComplete()
    }

    @Test
    fun populateDatabase_Error() {
        val throwable = Throwable("Error response")
        `when`(dormRepository.getDorms()).thenReturn(Single.error(throwable))
        dormInteractor.populateDatabase().test().assertError(throwable)
    }


    @Test
    fun populateDatabase_Success() {
        `when`(dormRepository.getDorms()).thenReturn(Single.just(emptyList()))

        dormInteractor.populateDatabase().test().assertValue(PopulateDatabaseResult.Success)
    }

    @Test
    fun populateDatabase_DatabaseAlreadyPopulated() {
        val dorms = listOf(DormEntity("", 1, 1, ""))
        `when`(dormRepository.getDorms()).thenReturn(Single.just(dorms))

        dormInteractor.populateDatabase().test().assertValue(PopulateDatabaseResult.DatabaseAlreadyPopulated)
    }
}