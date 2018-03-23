package com.fibear.android.fibear.utils

import org.joda.time.LocalDate
import org.joda.time.Years



/**
 * Created by TINH HUYNH on 3/23/2018.
 */
class DateUtils {
    companion object {
        fun calculateAge(year: Int, month: Int, day: Int): Int{
            val birthdate = LocalDate(year, month, day)
            val now = LocalDate()
            return Years.yearsBetween(birthdate, now).years
        }
    }
}