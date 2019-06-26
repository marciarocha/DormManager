package com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats

import okhttp3.Request
import okhttp3.Response

class NetworkStatsBuilderFactory<T> {

    fun createNetworkStatsBuilder(value: T): NetworkStatsBuilder {
        return when (value) {
            is Request -> RequestParser(
                value
            )
            is Response -> ResponseParser(
                value
            )
            else -> throw IllegalStateException()
        }
    }
}