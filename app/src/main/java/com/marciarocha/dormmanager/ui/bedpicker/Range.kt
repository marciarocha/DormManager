package com.marciarocha.dormmanager.ui.bedpicker


class Range(
    private val min: Int,
    private val max: Int
) {

    fun toList(): List<Int> {
        val list = mutableListOf<Int>()

        for (i in min..max) {
            list.add(i)
        }
        return list
    }
}