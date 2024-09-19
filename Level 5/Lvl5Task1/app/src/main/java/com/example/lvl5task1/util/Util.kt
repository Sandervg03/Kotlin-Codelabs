package com.example.lvl5task1.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Util {
    companion object {

        fun dateToString(date: Date): String =
            SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date)

        fun dayMonthYearToDate(day: String, month: String, year: String): Date? {
            val dateString = StringBuilder()
            dateString.append(day)
                .append(month)
                .append(year)
            val format = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
            return format.parse(dateString.toString())
        }
    }
}