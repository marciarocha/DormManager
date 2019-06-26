package com.marciarocha.dormmanager.data.networking.interceptor


import com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats.ResponseParser
import com.marciarocha.dormmanager.domain.interactor.networkstats.NetworkStatsInteractor
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkStatsInterceptor @Inject constructor(private val networkStatsInteractor: NetworkStatsInteractor) :
    Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val networkStats = ResponseParser(response).buildNetworkStats()
            networkStatsInteractor.sendNetworkStats(networkStats)

        return response
    }
}