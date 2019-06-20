package com.marciarocha.dormmanager.domain.state

import com.marciarocha.dormmanager.domain.model.Dorm

sealed class DormResult {
    data class Success(val dorms: List<Dorm>) : DormResult()
    object NoResults : DormResult()
}