package com.marciarocha.dormmanager.data.repository.networkStats

import com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats.NetworkStats
import io.reactivex.Single
import okhttp3.ResponseBody

interface NetworkStatsRepository {
    fun sendNetworkStats(networkStats: NetworkStats): Single<ResponseBody>
}