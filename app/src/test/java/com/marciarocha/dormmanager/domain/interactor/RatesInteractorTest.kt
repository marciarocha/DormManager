package com.marciarocha.dormmanager.domain.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marciarocha.dormmanager.data.networking.RatesResponse
import com.marciarocha.dormmanager.data.repository.rates.RatesRepository
import com.marciarocha.dormmanager.domain.interactor.rates.RatesInteractorImpl
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.util.*

class RatesInteractorTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val ratesRepository = mock(RatesRepository::class.java)
    private val ratesInteractor by lazy { RatesInteractorImpl(ratesRepository) }

    @Test
    fun getExchangeRates_Completed() {
        val map = HashMap<String, Double>()
        map[""] = 1.0
        val response = RatesResponse("", "", map)
        Mockito.`when`(ratesRepository.getExchangeRates("")).thenReturn(Single.just(response))

        ratesInteractor.getExchangeRates("").test().assertComplete()
    }

    @Test
    fun getExchangeRates_Error() {
        val throwable = Throwable("error message")
        Mockito.`when`(ratesRepository.getExchangeRates("")).thenReturn(Single.error(throwable))

        ratesInteractor.getExchangeRates("").test().assertError(throwable)
    }

    @Test
    fun getExchangeRates_Response() {
        val map = HashMap<String, Double>()
        map["currency1"] = 1.0
        map["currency2"] = 2.0
        val response = RatesResponse("", "", map)
        Mockito.`when`(ratesRepository.getExchangeRates("")).thenReturn(Single.just(response))

        ratesInteractor.getExchangeRates("")
            .test().assertValue(listOf("currency1", "currency2"))
    }

    @Test
    fun getExchangeRate_Completed() {
        Mockito.`when`(ratesRepository.getExchangeRate("")).thenReturn(Single.just(1.0))
        ratesInteractor.convertCurrency(2.3, "").test().assertComplete()
    }

    @Test
    fun getExchangeRate_Error() {
        val throwable = Throwable("error message")
        Mockito.`when`(ratesRepository.getExchangeRate("")).thenReturn(Single.error(throwable))
        ratesInteractor.convertCurrency(2.3, "").test().assertError(throwable)
    }

    @Test
    fun getExchangeRate_Response() {
        Mockito.`when`(ratesRepository.getExchangeRate("")).thenReturn(Single.just(2.0))

        ratesInteractor.convertCurrency(2.24, "")
            .test().assertValue(4.48)
    }


}