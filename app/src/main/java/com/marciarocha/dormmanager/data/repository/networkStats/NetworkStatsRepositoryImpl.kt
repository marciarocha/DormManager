package com.marciarocha.dormmanager.data.repository.networkStats

import com.marciarocha.dormmanager.data.networking.api.networkstats.NetworkStatsApi
import com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats.NetworkStats
import io.reactivex.Single
import okhttp3.ResponseBody
import javax.inject.Inject

class NetworkStatsRepositoryImpl @Inject constructor(
    private val networkStatsApi: NetworkStatsApi
) : NetworkStatsRepository {
    override fun sendNetworkStats(networkStats: NetworkStats): Single<ResponseBody> {
        return networkStatsApi.sendRequestStats(networkStats.toHashMap())
    }
}