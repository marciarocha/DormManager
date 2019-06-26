package com.marciarocha.dormmanager.domain.interactor.networkstats

import com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats.NetworkStats
import io.reactivex.Single

interface NetworkStatsInteractor {

    fun sendNetworkStats(networkStats: NetworkStats): Single<String>
    fun cacheNetworkStats(networkStats: NetworkStats)
    fun getCachedNetworkStats(): Single<List<NetworkStats>>
    fun clearCache()
    fun sendCachedNetworkStats(): Single<String>
}