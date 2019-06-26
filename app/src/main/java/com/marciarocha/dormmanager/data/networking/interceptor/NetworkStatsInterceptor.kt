package com.marciarocha.dormmanager.data.networking.interceptor

import android.util.Log
import com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats.NetworkStatsBuilderFactory
import com.marciarocha.dormmanager.domain.interactor.networkstats.NetworkStatsInteractor
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkStatsInterceptor @Inject constructor(private val networkStatsInteractor: NetworkStatsInteractor) :
    Interceptor {
    private val compositeDisposable = CompositeDisposable()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val networkStats = NetworkStatsBuilderFactory<Response>()
            .createNetworkStatsBuilder(response).buildNetworkStats()

        compositeDisposable.add(
            networkStatsInteractor.sendNetworkStats(networkStats)
                .subscribe(
                    { networkStatsResponse -> Log.i("getNetworkStats()", networkStatsResponse) },
                    { Log.e("sendNetworkStats()", it.message) })
        )

        return response
    }
}