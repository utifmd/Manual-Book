package com.dudegenuine.manualbook.feature.di.module.utility

import java.text.SimpleDateFormat
import java.util.*

/**
 * Manual Book created by utifmd on 26/06/21.
 */
class UtilityModule {
    fun provideDateLastWeek(current: Date){
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.time = current
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        val newDate = calendar.time

        val date = dateFormat.format(newDate)
    }
}