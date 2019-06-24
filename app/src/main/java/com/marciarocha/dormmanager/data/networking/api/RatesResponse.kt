package com.marciarocha.dormmanager.data.networking.api

import com.google.gson.annotations.SerializedName

data class RatesResponse(
    @SerializedName("base") val base: String,
    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: HashMap<String, Double>
)
