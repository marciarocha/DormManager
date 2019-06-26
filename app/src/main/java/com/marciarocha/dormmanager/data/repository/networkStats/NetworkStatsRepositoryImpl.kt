package com.marciarocha.dormmanager.data.repository.networkStats

import com.marciarocha.dormmanager.data.networking.api.networkstats.NetworkStatsApi
import com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats.NetworkStats
import com.marciarocha.dormmanager.data.persistence.dao.NetworkStatsDao
import com.marciarocha.dormmanager.data.persistence.entity.NetworkStatsEntity
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import javax.inject.Inject

class NetworkStatsRepositoryImpl @Inject constructor(
    private val networkStatsApi: NetworkStatsApi,
    private val networkStatsDao: NetworkStatsDao
) : NetworkStatsRepository {
    override fun sendNetworkStats(networkStats: NetworkStats): Single<ResponseBody> {
        return networkStatsApi.sendRequestStats(networkStats.toHashMap())
    }


    override fun getNetworkStats(): Single<List<NetworkStatsEntity>> {
        return networkStatsDao.getNetworkStats().subscribeOn(Schedulers.io())
    }

    override fun clearCache() {
        return networkStatsDao.clearNetworkStats()
    }

    override fun cacheNetworkStats(networkStats: NetworkStatsEntity) {
        networkStatsDao.insertNetworkStat(networkStats)
    }
}