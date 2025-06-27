package com.hijakd.cactusnotes.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(time: Long): String{
    val date = Date(time)
    val format = SimpleDateFormat("EEE. d MMM y - HH:mm", Locale.getDefault())
    return format.format(date)
}

fun formatDate(time: Date): String{
//    val date = Date(time)
    val format = SimpleDateFormat("EEE. d MMM y - HH:mm", Locale.getDefault())
    return format.format(time)
}

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