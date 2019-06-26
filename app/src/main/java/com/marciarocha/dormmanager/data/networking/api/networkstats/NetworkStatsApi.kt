package com.marciarocha.dormmanager.data.networking.api.networkstats

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NetworkStatsApi {

    @GET("stats")
    fun sendRequestStats(@QueryMap stats: HashMap<String, String>)
}