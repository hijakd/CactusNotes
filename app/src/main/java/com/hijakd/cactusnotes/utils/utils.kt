package com.hijakd.cactusnotes.utils

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