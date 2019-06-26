package com.marciarocha.dormmanager.domain.interactor.networkstats

import com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats.NetworkStats

interface NetworkStatsInteractor {

    fun sendNetworkStats(networkStats: NetworkStats)
}