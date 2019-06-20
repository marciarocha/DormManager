package com.marciarocha.dormmanager.domain.state

import com.marciarocha.dormmanager.domain.model.Dorm

sealed class DatabaseResult {
    data class Success(val dorms: List<Dorm>) : DatabaseResult()
    object NoResults : DatabaseResult()
}