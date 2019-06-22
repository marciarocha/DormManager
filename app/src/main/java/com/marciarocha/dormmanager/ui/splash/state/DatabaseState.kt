package com.marciarocha.dormmanager.ui.splash.state

sealed class DatabaseState {
    object Loading : DatabaseState()
    object DatabaseLoaded : DatabaseState()
    object Error : DatabaseState()
}