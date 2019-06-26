package com.marciarocha.dormmanager.domain.interactor.networkstats

import com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats.NetworkStats
import com.marciarocha.dormmanager.data.repository.networkStats.NetworkStatsRepository
import io.reactivex.Single

class NetworkStatsInteractorImpl(private val networkStatsRepository: NetworkStatsRepository) : NetworkStatsInteractor {

    override fun sendNetworkStats(networkStats: NetworkStats): Single<String> {
        return networkStatsRepository.sendNetworkStats(networkStats)
            .map { body -> body.string() }
    }

}