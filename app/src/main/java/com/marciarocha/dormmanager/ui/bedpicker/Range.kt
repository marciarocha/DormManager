package com.marciarocha.dormmanager.ui.bedpicker


class Range(
    val min: Int,
    val max: Int
) {

    fun toList(): List<Int> {
        val list = mutableListOf<Int>()

        for (i in min..max) {
            list.add(i)
        }
        return list
    }
}