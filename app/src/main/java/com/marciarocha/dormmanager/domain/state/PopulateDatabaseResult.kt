package com.marciarocha.dormmanager.domain.state

sealed class PopulateDatabaseResult {
    object Error : PopulateDatabaseResult()
    object Success : PopulateDatabaseResult()
    object DatabaseAlreadySeeded : PopulateDatabaseResult()
}