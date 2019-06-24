package com.marciarocha.dormmanager.data.networking.interceptor

import android.util.Log
import com.marciarocha.dormmanager.data.repository.networkStats.NetworkStatsRepository
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkStatsInterceptor @Inject constructor(private val networkStatsRepository: NetworkStatsRepository) :
    Interceptor {
    private val compositeDisposable = CompositeDisposable()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val networkStats = ResponseParser(response).buildNetworkStats()

        compositeDisposable.add(
            networkStatsRepository.sendNetworkStats(networkStats)
                .subscribe({ response -> Log.i("getNetworkStats()", response.string()) },
                    { Log.e("senNetworkStats()", it.message) })
        )

        return response
    }
}