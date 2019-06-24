package com.marciarocha.dormmanager.data.networking.interceptor

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtil(applicationContext: Context) {

    private var connectivityManager: ConnectivityManager =
        applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isOnline(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}