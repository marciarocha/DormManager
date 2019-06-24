package com.marciarocha.dormmanager.data.repository.networkStats

import com.marciarocha.dormmanager.data.networking.api.networkstats.NetworkStats
import com.marciarocha.dormmanager.data.networking.api.networkstats.NetworkStatsApi
import io.reactivex.Single
import okhttp3.ResponseBody

class NetworkStatsRepositoryImpl(private val networkStatsApi: NetworkStatsApi) : NetworkStatsRepository {
    override fun sendNetworkStats(networkStats: NetworkStats): Single<ResponseBody> {
        return networkStatsApi.sendRequestStats(networkStats.toHashMap())
    }
}