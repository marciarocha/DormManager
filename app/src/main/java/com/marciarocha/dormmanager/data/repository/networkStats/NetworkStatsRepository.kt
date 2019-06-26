package com.marciarocha.dormmanager.data.repository.networkStats

import com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats.NetworkStats

interface NetworkStatsRepository {
    fun sendNetworkStats(networkStats: NetworkStats)
}