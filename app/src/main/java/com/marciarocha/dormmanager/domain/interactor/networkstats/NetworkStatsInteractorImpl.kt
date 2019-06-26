package com.marciarocha.dormmanager.domain.interactor.networkstats

import com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats.NetworkStats
import com.marciarocha.dormmanager.data.repository.networkStats.NetworkStatsRepository

class NetworkStatsInteractorImpl(private val networkStatsRepository: NetworkStatsRepository) : NetworkStatsInteractor {

    override fun sendNetworkStats(networkStats: NetworkStats) {
        return networkStatsRepository.sendNetworkStats(networkStats)
    }

}