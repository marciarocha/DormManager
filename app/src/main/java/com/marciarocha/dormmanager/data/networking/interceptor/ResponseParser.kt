package com.marciarocha.dormmanager.data.networking.interceptor

import android.util.Base64
import okhttp3.Response

class ResponseParser(private val response: Response) {

    fun getDuration(): Long {
        return this.response.receivedResponseAtMillis() - this.response.sentRequestAtMillis()
    }

    fun getBase64URL(): String {
        val url = this.response.request().url().toString()
        val data = url.toByteArray(Charsets.UTF_8)
        return Base64.encodeToString(data, Base64.DEFAULT)
    }

    fun getStatus(): String {
        return if (this.response.isSuccessful) {
            "SUCCESS"
        } else {
            "ERROR"
        }
    }

    fun buildNetworkStats(): NetworkStats {
        return NetworkStats(getDuration().toString(), getBase64URL(), getStatus())
    }
}