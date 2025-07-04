package com.hijakd.cactusnotes.utils

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun dateToTimestamp(date: Date): Long{
        return date.time
    }

//    @TypeConverter
//    fun dateToEditedTimestamp(date: Date): Long{
//        return date.time
//    }

    @TypeConverter
    fun timestampToDate(timestamp: Long): Date?{
        return Date(timestamp)
    }

//    @TypeConverter
//    fun editedTimestampToDate(editedTimestamp: Long): Date?{
//        return Date(editedTimestamp)
//    }

}