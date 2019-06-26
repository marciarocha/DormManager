package com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats


interface NetworkStatsBuilder {

    fun getDuration(): Long
    fun getBase64URL(): String
    fun getStatus(): String
    fun buildNetworkStats(): NetworkStats
}