package com.marciarocha.dormmanager.ui.main

import com.marciarocha.dormmanager.domain.model.Dorm

sealed class AvailableDormsState {
    object Loading : AvailableDormsState()
    object Error : AvailableDormsState()
    data class Loaded(val dorms: List<Dorm>) : AvailableDormsState()
}