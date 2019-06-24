package com.marciarocha.dormmanager.data.networking.interceptor


import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ConnectivityInterceptor @Inject constructor(private val networkUtil: NetworkUtil) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkUtil.isOnline()) {

        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}