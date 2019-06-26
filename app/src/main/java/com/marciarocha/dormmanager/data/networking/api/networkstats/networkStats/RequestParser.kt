package com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats

import android.util.Base64
import okhttp3.Request

class RequestParser(private val request: Request) :
    NetworkStatsBuilder {
    override fun getDuration(): Long {
        return 0
    }

    override fun getBase64URL(): String {
        val url = this.request.url().toString()
        val data = url.toByteArray(Charsets.UTF_8)
        return "load-" + Base64.encodeToString(data, Base64.DEFAULT)
    }

    override fun getStatus(): String {
        return "NO_NETWORK"
    }

    override fun buildNetworkStats(): NetworkStats {
        return NetworkStats(
            getDuration().toString(),
            getBase64URL(),
            getStatus()
        )
    }
}