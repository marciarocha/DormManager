package com.marciarocha.dormmanager.data.networking.interceptor


import com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats.NetworkStatsBuilderFactory
import com.marciarocha.dormmanager.domain.interactor.networkstats.NetworkStatsInteractor
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class ConnectivityInterceptor @Inject constructor(
    private val networkUtil: NetworkUtil,
    private val networkStatsInteractor: NetworkStatsInteractor
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (!networkUtil.isOnline()) {
            val networkStats = NetworkStatsBuilderFactory<Request>()
                .createNetworkStatsBuilder(request).buildNetworkStats()
            networkStatsInteractor.cacheNetworkStats(networkStats)
        }

        return response
    }
}