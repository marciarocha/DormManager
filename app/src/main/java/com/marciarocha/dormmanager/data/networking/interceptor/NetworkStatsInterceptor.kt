package com.marciarocha.dormmanager.data.networking.interceptor

import okhttp3.*

class NetworkStatsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val networkStats = ResponseParser(response).buildNetworkStats()

        val urlBuilder =
            HttpUrl.parse("https://gist.githubusercontent.com/ruimendesM/cb9313c4d4b3434975a3d7a6700d1787/raw/0 2d17a4c542ac99fe559df360cbfe9ba24dbe6be/stats")
                ?.newBuilder()
                ?.addQueryParameter("duration", networkStats.duration)
                ?.addQueryParameter("status", networkStats.status)
                ?.addQueryParameter("action", networkStats.action)?.build()

        val newRequest = Request.Builder().url(urlBuilder).build()
        val okHttpClient = OkHttpClient()
        okHttpClient.newCall(newRequest).execute()

        return response
    }
}