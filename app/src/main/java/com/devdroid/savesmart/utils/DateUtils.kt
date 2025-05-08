package com.devdroid.savesmart.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun getCurrentMonthYear(): String {
        return SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(Date())
    }

    // You can add other date-related utility functions here
}