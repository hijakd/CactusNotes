package com.hijakd.cactusnotes.utils

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun dateToTimestamp(date: Date): Long{
        return date.time
    }

    @TypeConverter
    fun timestampToDate(timestamp: Long): Date?{
        return Date(timestamp)
    }
}