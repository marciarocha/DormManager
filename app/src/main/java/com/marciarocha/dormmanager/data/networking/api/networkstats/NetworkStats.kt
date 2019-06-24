package com.marciarocha.dormmanager.data.networking.api.networkstats

data class NetworkStats(
    val duration: String,
    val action: String,
    val status: String
) {
    fun toHashMap(): HashMap<String, String> {
        val map = HashMap<String, String>()
        map["duration"] = this.duration
        map["action"] = this.action
        map["status"] = this.status

        return map
    }
}