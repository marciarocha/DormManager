package com.marciarocha.dormmanager.data.networking.api.networkstats.networkStats

import android.util.Base64
import okhttp3.Response

class ResponseParser(private val response: Response) :
    NetworkStatsBuilder {

    override fun getDuration(): Long {
        return this.response.receivedResponseAtMillis() - this.response.sentRequestAtMillis()
    }

    override fun getBase64URL(): String {
        val url = this.response.request().url().toString()
        val data = url.toByteArray(Charsets.UTF_8)
        return "load-" + Base64.encodeToString(data, Base64.DEFAULT)
    }

    override fun getStatus(): String {
        return if (this.response.isSuccessful) {
            "SUCCESS"
        } else {
            "ERROR"
        }
    }

    override fun buildNetworkStats(): NetworkStats {
        return NetworkStats(
            getDuration().toString(),
            getBase64URL(),
            getStatus()
        )
    }
}