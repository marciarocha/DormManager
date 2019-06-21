package com.marciarocha.dormmanager.ui.bedpicker

import com.marciarocha.dormmanager.domain.model.Dorm

interface OnDialogResultListener {
    fun onDialogResult(dorm: Dorm, result: Int)
}