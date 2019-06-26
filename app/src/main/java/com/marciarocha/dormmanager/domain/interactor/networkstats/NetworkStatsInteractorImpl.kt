package com.marciarocha.dormmanager.domain.interactor.networkstats

import com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats.NetworkStats
import com.marciarocha.dormmanager.data.persistence.entity.NetworkStatsEntity
import com.marciarocha.dormmanager.data.repository.networkStats.NetworkStatsRepository
import io.reactivex.Single

class NetworkStatsInteractorImpl(private val networkStatsRepository: NetworkStatsRepository) : NetworkStatsInteractor {

    override fun sendNetworkStats(networkStats: NetworkStats): Single<String> {
        return networkStatsRepository.sendNetworkStats(networkStats)
            .map { responseBody -> responseBody.string() }
    }

    override fun cacheNetworkStats(networkStats: NetworkStats) {
        val networkStatsEntity = NetworkStatsEntity(networkStats.duration, networkStats.action, networkStats.status)
        networkStatsRepository.cacheNetworkStats(networkStatsEntity)
    }

    override fun getCachedNetworkStats(): Single<List<NetworkStats>> {
        return networkStatsRepository.getNetworkStats()
            .map { list -> list.map { entity -> NetworkStats(entity.duration, entity.action, entity.duration) } }
    }

    override fun clearCache() {
        networkStatsRepository.clearCache()
    }

    override fun sendCachedNetworkStats(): Single<String> {
        return getCachedNetworkStats()
            .toObservable()
            .flatMapIterable { it }
            .flatMap { sendNetworkStats(it).toObservable() }
            .singleOrError()
    }

}