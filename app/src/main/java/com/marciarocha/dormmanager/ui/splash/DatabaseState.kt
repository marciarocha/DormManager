package com.marciarocha.dormmanager.ui.splash

sealed class DatabaseState {
    object Loading : DatabaseState()
    object DatabaseLoaded : DatabaseState()
    object Error : DatabaseState()
}